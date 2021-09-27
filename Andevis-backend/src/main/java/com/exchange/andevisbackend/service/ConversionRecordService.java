package com.exchange.andevisbackend.service;

import com.exchange.andevisbackend.DTO.Record;
import com.exchange.andevisbackend.entity.User;
import com.exchange.andevisbackend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConversionRecordService {

    private final UserAccountRepository userRepository;

    @Autowired
    public ConversionRecordService(UserAccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Record> findRecordsByUser(Long userId) {

        User theUser = userRepository.findById(userId).orElseThrow();

        List<Record> records = new ArrayList<>();

        theUser.getRecordsHistory().forEach(r->{
            Record record = new Record();
            record.setSourceCurrency(r.getSourceCurrency().getCurrencyName());
            record.setTargetCurrency(r.getTargetCurrency().getCurrencyName());
            record.setAmount(r.getAmount());
            record.setCalculatedRate(r.getCalculatedRate());
            record.setRequestTime(r.getConversionDate());
            records.add(record);
        });

        return records;
    }
}
