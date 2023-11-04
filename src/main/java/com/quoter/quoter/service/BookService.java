package com.quoter.quoter.service;

import com.quoter.quoter.dto.BookDto;
import com.quoter.quoter.dto.ResultDto;
import com.quoter.quoter.model.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface BookService {
    BookDto randomBook(int range, String urlFormat) throws IOException;

}
