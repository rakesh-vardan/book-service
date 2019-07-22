package com.epam.jpop.bookservice.controller;

import com.epam.jpop.bookservice.domain.Book;
import com.epam.jpop.bookservice.exception.BookIdMismatchException;
import com.epam.jpop.bookservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> list() {
        return new ResponseEntity<>(bookService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.show(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Book book) {
        bookService.add(book);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Book book) throws BookIdMismatchException {
        bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
