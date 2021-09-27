package com.exchange.andevisbackend.util;

import com.exchange.andevisbackend.common.CurrencyCode;
import com.exchange.andevisbackend.entity.Currency;
import com.exchange.andevisbackend.exceptions.XMLParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.dom.DOMElement;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class CurrencyDataUtil {

    private static final Logger LOG = LogManager.getLogger(CurrencyDataUtil.class);


    public File downloadFile(URL url, String fileName) throws IOException {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        }
        return Paths.get(fileName).toFile();
    }

    public SortedSet<Currency> parseXML(File fileXML) {


        SortedSet<Currency> dataSet = new TreeSet<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ratesDate = LocalDate.parse(LocalDate.now().format(dtf), dtf);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(fileXML);

            doc.getDocumentElement().normalize();

            Element parent = (Element) doc.getElementsByTagName("Cube").item(0);

            NodeList children = parent.getElementsByTagName("Cube");

            for (int tmp = 0; tmp < children.getLength(); tmp++) {

                if (tmp == 0) {
                    Element time = (Element) children.item(tmp);
                    DateTimeFormatter ecb = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LOG.info("Actual date in XML file: " + LocalDate.parse(time.getAttribute("time"), ecb));
                    continue;
                }
                Element el = (Element) children.item(tmp);
                CurrencyCode code = CurrencyCode.valueOf(el.getAttribute("currency"));
                String rate = el.getAttribute("rate");
                Currency currency = new Currency(code, code.getName(), rate, ratesDate, LocalTime.now());
                dataSet.add(currency);
            }
            Currency baseCurrency = new Currency(CurrencyCode.EUR, "Euro", "1", ratesDate, LocalTime.now());
            dataSet.add(baseCurrency);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.warn(e.getMessage());
            throw new XMLParseException("Error occurred during XML file parsing: "+e.getMessage());
        }

        return dataSet;
    }


    public LocalDate getDate(File fileXML) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(fileXML);

            doc.getDocumentElement().normalize();

            Element parent = (Element) doc.getElementsByTagName("Cube").item(0);

            NodeList children = parent.getElementsByTagName("Cube");

            Element time = (Element) children.item(0);

            return LocalDate.parse(time.getAttribute("time"), dtf);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOG.warn(e.getMessage());
            throw new XMLParseException("Error occurred during XML file parsing "+e.getMessage());

        }
    }
}
