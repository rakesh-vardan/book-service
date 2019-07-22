package com.epam.jpop.bookservice.service.impl;

import com.epam.jpop.bookservice.domain.Book;
import com.epam.jpop.bookservice.exception.BookIdMismatchException;
import com.epam.jpop.bookservice.exception.BookNotFoundException;
import com.epam.jpop.bookservice.repository.BookRepository;
import com.epam.jpop.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> list() {
        List<com.epam.jpop.bookservice.entity.Book> booksFromEntity = bookRepository.findAll();

        return booksFromEntity.stream()
                .map(b -> new Book(b.getId(), b.getTitle(), b.getAuthor(),
                        b.getIsbn(), b.getPublisher(), b.getPublishedYear()))
                .collect(Collectors.toList());
    }

    @Override
    public Book show(Long id) {
        Book book = new Book();

        com.epam.jpop.bookservice.entity.Book bookEntity = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthor(bookEntity.getAuthor());

        Optional<Long> isbn = Optional.ofNullable(bookEntity.getIsbn());
        isbn.ifPresent(book::setIsbn);

        Optional<String> publisher = Optional.ofNullable(bookEntity.getPublisher());
        publisher.ifPresent(book::setPublisher);

        Optional<Integer> publishedYear = Optional.ofNullable(bookEntity.getPublishedYear());
        publishedYear.ifPresent(book::setPublishedYear);

        return book;
    }

    @Override
    public void add(Book book) {
        bookRepository.save(prepareBookEntity(book));
    }

    @Override
    public void update(Long id, Book book) throws BookIdMismatchException {
        if (!book.getId().equals(id)) {
            throw new BookIdMismatchException();
        }

        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.save(prepareBookEntity(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    private com.epam.jpop.bookservice.entity.Book prepareBookEntity(Book book) {
        com.epam.jpop.bookservice.entity.Book bookEntity = new com.epam.jpop.bookservice.entity.Book();

        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());

        Optional<Long> isbn = Optional.ofNullable(book.getIsbn());
        isbn.ifPresent(bookEntity::setIsbn);

        Optional<String> publisher = Optional.ofNullable(book.getPublisher());
        publisher.ifPresent(bookEntity::setPublisher);

        Optional<Integer> publishedYear = Optional.ofNullable(book.getPublishedYear());
        publishedYear.ifPresent(bookEntity::setPublishedYear);

        return bookEntity;
    }
}
