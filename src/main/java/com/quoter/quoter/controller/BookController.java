package com.quoter.quoter.controller;

import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import com.quoter.quoter.repository.BookRepository;
import com.quoter.quoter.repository.CustomBookRepository;
import com.quoter.quoter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ResultDto> getBook(@RequestParam("range") int range,
            @RequestParam("urlFormat") String urlFormat)
            throws IOException {// title'a ya da başka alanlara göre de getirilebilir
        ResultDto res = bookService.randomBook(range, urlFormat);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
