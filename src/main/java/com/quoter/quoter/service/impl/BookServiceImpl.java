package com.quoter.quoter.service.impl;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Value("${gutendex.endpoint}")
    private String gutenbergEndpoint ;//move it to the config file

    @Value("${gutenberg.plain.format}")
    private String bookPlainTextFormat;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    WebScrapeService webScrapeService;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public ResultDto randomBook(int range, String urlFormat) throws IOException {//get the id, fetch the text, parse it according to the given indexes
        //gelen 500 hatası incelenip 500 hatası geldiğibnde tekrar istek atılacak şekilde düzenlenecek
        RestTemplate restTemplate = new RestTemplate();
        Random r = new Random();
        String gResponse = restTemplate.getForEntity(gutenbergEndpoint,String.class).getBody();

        Map responseMap = getBookInformationJsonToMap(gResponse);

        int count = (int) getFieldFromJson(gResponse, "count");
        int randomGeneratedValue = r.nextInt(count);


        String bookInformation = restTemplate.getForEntity(gutenbergEndpoint + "/" + randomGeneratedValue,String.class).getBody();
        Object textFormats = getFieldFromJson(bookInformation,"formats");

        String plainUrl = getFormat(textFormats,urlFormat);


        return new ResultDto(getRandomChunkFromBook(Jsoup.connect(plainUrl).get().html(),range),generateBook(bookInformation,getFormat(textFormats,"image/jpeg")));

    }
    private String getPlainTextUrl(int bookId){
        RestTemplate restTemplate = new RestTemplate();
        String gJson = restTemplate.getForEntity(gutenbergEndpoint+bookId,String.class).getBody();
        return gJson;
    }
    private String getRandomChunkFromBook(String html, int chunkSize){
        Document document = Jsoup.parseBodyFragment(html);
        Element body = document.body();
        Elements text = body.getElementsByTag("body");
        String textString = "";
        Random r = new Random();
        int startingIndex;
        int endingIndex;

        for (Element simple : text){
            textString = simple.text();
        }

        startingIndex = r.ints(300, textString.length() - chunkSize)
                .findFirst()
                .getAsInt();

        return textString.substring(startingIndex,startingIndex + chunkSize);
    }

    //https://www.gutenberg.org/cache/epub/69640/pg69640-images.html
    //https://www.gutenberg.org/cache/epub/69638/pg69638-images.html
    //https://www.gutenberg.org/cache/epub/69638/pg69638.txt -> plain text
    //https://www.gutenberg.org/cache/epub/2643/pg2643.txt -> id'yi artırarak ilerleyince sonuç veriyor
    private Object getFieldFromJson(String json,String field){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map.get(field);
        }catch(Exception e){
            logger.error("error while parsing json:" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    private Map getBookInformationJsonToMap(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map;
        }catch(Exception e){
            logger.error("error while parsing json:" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    private String getFormat(Object map,String format){
        LinkedHashMap hashMap = (LinkedHashMap) map;
        String url = (String) hashMap.get((hashMap.keySet().stream().filter(key -> key.toString().contains(format)).findFirst()).get());
        return url;
    }
    private Book generateBook(String fields, String pictureUrl){
        Book book = new Book();

        book.setId(((Integer)getFieldFromJson(fields,"id")).longValue());
        book.setName((String)getFieldFromJson(fields,"title"));
        book.setDescription("");//work in progress
        book.setPictureUrl(pictureUrl);

        return book;
    }

}
