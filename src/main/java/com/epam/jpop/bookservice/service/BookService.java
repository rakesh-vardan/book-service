package com.epam.jpop.bookservice.service;

import com.epam.jpop.bookservice.domain.Book;
import com.epam.jpop.bookservice.exception.BookIdMismatchException;

import java.util.List;

public interface BookService {

    List<Book> list();

    Book show(Long id);

    void add(Book book);

    void update(Long id, Book book) throws BookIdMismatchException;

    void delete(Long id);
}
