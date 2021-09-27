package com.exchange.andevisbackend.service;

import com.exchange.andevisbackend.DTO.RateResponse;
import com.exchange.andevisbackend.common.CurrencyCode;
import com.exchange.andevisbackend.entity.ConversionRecord;
import com.exchange.andevisbackend.entity.Currency;
import com.exchange.andevisbackend.entity.User;
import com.exchange.andevisbackend.exceptions.NoCurrencyDataException;
import com.exchange.andevisbackend.exceptions.NoUserFoundException;
import com.exchange.andevisbackend.repository.CurrencyRepository;
import com.exchange.andevisbackend.repository.UserAccountRepository;
import com.exchange.andevisbackend.util.CurrencyDataUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;

@Service
public class ExchangeService {

    private final CurrencyRepository currencyRepository;

    private final UserAccountRepository userRepository;

    private final CurrencyDataUtil currencyData;

    private static final Logger LOG = LogManager.getLogger(ExchangeService.class);


    @Value("${ecb.url}")
    private String URL;

    @Autowired
    public ExchangeService(CurrencyRepository currencyRepository, CurrencyDataUtil currencyData, UserAccountRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyData = currencyData;
        this.userRepository = userRepository;
    }

    public Set<Currency> fetchCurrencyData() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate today = LocalDate.parse(LocalDate.now().format(dtf),dtf);

       return refreshRates(today);

    }

    public RateResponse fetchRates(CurrencyCode sourceCode, CurrencyCode targetCode, Long userId, String amount) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(LocalDate.now().format(dtf),dtf);

        RateResponse response = new RateResponse();

       Currency source = currencyRepository.findByCurrencyCodeAndRateDate(sourceCode,today);

       Currency target = currencyRepository.findByCurrencyCodeAndRateDate(targetCode,today);

       if(source==null || target == null) {
           Set<Currency> latest = refreshRates(today);
           source = latest.stream().filter(c->c.getCurrencyCode().equals(sourceCode)).findFirst()
                   .orElseThrow(()->{
                       LOG.warn("No data for "+sourceCode+" available");
                   return new NoCurrencyDataException("Unable to find data for requested currency");
       });

           target = latest.stream().filter(c->c.getCurrencyCode().equals(targetCode)).findFirst()
                   .orElseThrow(()->{
                       LOG.warn("No data for "+targetCode+" available");
                       return new NoCurrencyDataException("Unable to find data for requested currency");
                   });
       }
        User theUser = userRepository.findById(userId).orElseThrow(()->{
            LOG.warn("No user found with ID: "+userId);
            return new NoUserFoundException("Unable to find user with ID "+userId);
                });

       String calculatedRate = calculateRate(source, target, amount);

       response.setSourceCurrencyName(source.getCurrencyName());
       response.setTargetCurrencyName(target.getCurrencyName());
       response.setCalculatedRate(calculatedRate);

        ConversionRecord theRecord = new ConversionRecord();
        theRecord.setTargetCurrency(target);
        theRecord.setSourceCurrency(source);
        theRecord.setAmount(amount);
        theRecord.setCalculatedRate(calculatedRate);
        theRecord.setUser(theUser);
        theRecord.setConversionDate(LocalDateTime.now());
        theUser.addRecord(theRecord);

        userRepository.save(theUser);

        return response;
    }

    private String calculateRate (Currency source, Currency target, String amount){

        BigDecimal sourceRate = new BigDecimal(source.getBaseRate());

        BigDecimal targetRate = new BigDecimal(target.getBaseRate());

        BigDecimal amountValue = new BigDecimal(amount);

        BigDecimal calculatedRate = targetRate.divide(sourceRate,10, RoundingMode.CEILING)
                .multiply(amountValue).setScale(3,RoundingMode.FLOOR);

        return String.valueOf(calculatedRate);
    }

    public Set<Currency> refreshRates (LocalDate today) {

        SortedSet<Currency> latestFromDb = currencyRepository.findAllByRateDate(today);

        try {
            File dataDir = new File("data");

            if (!dataDir.exists()) {
                Files.createDirectory(dataDir.toPath());
            }

            File fileXML = currencyData.downloadFile(new URL(URL),
                    dataDir.getPath() + "/rates-" + today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            LocalDate dateInFile = currencyData.getDate(fileXML);

            SortedSet<Currency> latestFromEcb = currencyData.parseXML(fileXML);

            if ((today.equals(dateInFile)) && !latestFromDb.equals(latestFromEcb)) {
                iterateSimultaneously(latestFromDb, latestFromEcb, (d, e) -> {
                    d.setBaseRate(e.getBaseRate());
                });
                return new TreeSet<>(currencyRepository.saveAll(latestFromDb));

            } else if (!latestFromDb.equals(latestFromEcb)) {

                return new TreeSet<>(currencyRepository.saveAll(latestFromEcb));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return latestFromDb;
    }


    private static <T1, T2> void iterateSimultaneously(Iterable<T1> c1, Iterable<T2> c2, BiConsumer<T1, T2> consumer) {
        Iterator<T1> i1 = c1.iterator();
        Iterator<T2> i2 = c2.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            consumer.accept(i1.next(), i2.next());
        }
    }

}
