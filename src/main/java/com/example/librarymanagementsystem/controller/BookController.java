package com.example.librarymanagementsystem.comtroller;

import com.example.librarymanagementsystem.entity.Author;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.service.AuthorService;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Book book) throws Exception {
        return bookService.addBook(book);
    }

}
