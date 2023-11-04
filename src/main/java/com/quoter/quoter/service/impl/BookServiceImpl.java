package com.quoter.quoter.service.impl;

import com.quoter.quoter.dto.BookDto;
import com.quoter.quoter.dto.ExtApiResponse;
import com.quoter.quoter.dto.RangeDto;
import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.service.BookService;
import com.quoter.quoter.service.WebScrapeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Value("${gutendex.endpoint}")
    private String gutenbergEndpoint;// move it to the config file

    @Value("${gutenberg.plain.format}")
    private String bookPlainTextFormat;

    @Autowired
    BookRepository bookRepository;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public BookDto randomBook(int range, String urlFormat) throws IOException {// get the id, fetch the text, parse it
                                                                               // according to the given indexes
        // gelen 500 hatası incelenip 500 hatası geldiğibnde tekrar istek atılacak
        // şekilde düzenlenecek
        RestTemplate restTemplate = new RestTemplate();
        Random r = new Random();
        String gResponse = restTemplate.getForEntity(gutenbergEndpoint, String.class).getBody();
        ExtApiResponse extResponse = (ExtApiResponse) getBookInformation(gResponse, ExtApiResponse.class);

        int randomGeneratedValue = r.nextInt(Integer.parseInt(extResponse.getCount()));
        String bookInformation = restTemplate.getForEntity(gutenbergEndpoint + "/" + randomGeneratedValue, String.class)
                .getBody();
        BookDto b = (BookDto) getBookInformation(bookInformation, BookDto.class);
        String plainUrl = (String) getFormats(bookInformation, "text/plain");// getFormat(textFormats, urlFormat);
        b.setLine(getRandomChunkFromBook(Jsoup.connect(plainUrl).get().html(), range));
        b.setUrlFormat((String) getFormats(bookInformation, "image/jpeg"));
        return b;

    }

    private String getRandomChunkFromBook(String html, int chunkSize) {
        Document document = Jsoup.parseBodyFragment(html);
        Element body = document.body();
        Elements text = body.getElementsByTag("body");
        String textString = "";
        Random r = new Random();
        int startingIndex;

        for (Element simple : text) {
            textString = simple.text();
        }

        startingIndex = r.ints(300, textString.length() - chunkSize)
                .findFirst()
                .getAsInt();

        return textString.substring(startingIndex, startingIndex + chunkSize);
    }

    private Object getBookInformation(String json, Class c) {
        Object o = new Object();
        ObjectMapper mapper = new ObjectMapper();
        try {
            o = mapper.readValue(json, c);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFormats(String json, String formatName) {
        ObjectMapper mapper = new ObjectMapper();
        String format = "";
        try {
            Map<Object, Object> map = mapper.readValue(json, Map.class);
            Map<Object, Object> formats = (Map<Object, Object>) map.get("formats");
            for (Map.Entry<Object, Object> entry : formats.entrySet()) {
                format = ((String) entry.getKey()).equals(formatName) ? (String) entry.getValue() : "";
                if (!format.equals(""))
                    break;
            }
            return format;
        } catch (JsonMappingException e) {
            return "";
            // TODO Auto-generated catch block
        } catch (JsonProcessingException e) {
            return "";
            // TODO Auto-generated catch block
        }
    }
}
