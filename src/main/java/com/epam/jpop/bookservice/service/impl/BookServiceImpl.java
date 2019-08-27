package com.epam.jpop.bookservice.service.impl;

import com.epam.jpop.bookservice.domain.*;
import com.epam.jpop.bookservice.exception.BookIdMismatchException;
import com.epam.jpop.bookservice.exception.BookNotFoundException;
import com.epam.jpop.bookservice.repository.BookRepository;
import com.epam.jpop.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
                .map(b -> new Book(b.getId(), b.getTitle(), getAuthorFromEntity(b.getAuthor()),
                        getCategoryFromEntity(b.getCategory()), b.getIsbn(), getPublisherFromEntity(b.getPublisher()),
                        b.getPublishedDate(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Book show(Long id) {
        Book book = new Book();

        com.epam.jpop.bookservice.entity.Book bookEntity = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthor(getAuthorFromEntity(bookEntity.getAuthor()));
        book.setCategory(getCategoryFromEntity(bookEntity.getCategory()));

        Optional<Long> isbn = Optional.ofNullable(bookEntity.getIsbn());
        isbn.ifPresent(book::setIsbn);

        Optional<Publisher> publisher = Optional.ofNullable(getPublisherFromEntity(bookEntity.getPublisher()));
        publisher.ifPresent(book::setPublisher);

        Optional<Date> publishedYear = Optional.ofNullable(bookEntity.getPublishedDate());
        publishedYear.ifPresent(book::setPublishedDate);

        Optional<Double> price = Optional.ofNullable(bookEntity.getPrice());
        price.ifPresent(book::setPrice);

        return book;
    }

    @Override
    public Result add(Book book) {
        com.epam.jpop.bookservice.entity.Book bookEntity = bookRepository.save(prepareBookEntity(book));
        return new Result(bookEntity.getId());
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
        bookEntity.setAuthor(getAuthorEntityFromDTO(book.getAuthor()));
        bookEntity.setCategory(getCategoryEntityFromDTO(book.getCategory()));

        Optional<Long> isbn = Optional.ofNullable(book.getIsbn());
        isbn.ifPresent(bookEntity::setIsbn);

        Optional<com.epam.jpop.bookservice.entity.Publisher> publisher = Optional
                .of(getPublisherEntityFromDTO(book.getPublisher()));
        publisher.ifPresent(bookEntity::setPublisher);

        Optional<Date> publishedYear = Optional.ofNullable(book.getPublishedDate());
        publishedYear.ifPresent(bookEntity::setPublishedDate);

        Optional<Double> price = Optional.ofNullable(book.getPrice());
        price.ifPresent(bookEntity::setPrice);

        return bookEntity;
    }

    private Author getAuthorFromEntity(com.epam.jpop.bookservice.entity.Author authorEntity) {
        Author author = new Author();
        author.setCode(authorEntity.getAuthorCode());
        author.setName(authorEntity.getAuthorName());
        author.setAddress(authorEntity.getAuthorAddress());
        author.setPhoneNumber(authorEntity.getAuthorPhoneNumber());
        return author;
    }

    private Category getCategoryFromEntity(com.epam.jpop.bookservice.entity.Category categoryEntity) {
        Category category = new Category();
        category.setCode(categoryEntity.getCategoryCode());
        category.setName(categoryEntity.getCategoryName());
        category.setDesc(categoryEntity.getCategoryDescription());
        return category;
    }

    private Publisher getPublisherFromEntity(com.epam.jpop.bookservice.entity.Publisher publisherFromEntity) {
        Publisher publisher = new Publisher();
        publisher.setCode(publisherFromEntity.getPublisherCode());
        publisher.setName(publisherFromEntity.getPublisherName());
        publisher.setDesc(publisherFromEntity.getPublisherDescription());
        return publisher;
    }

    private com.epam.jpop.bookservice.entity.Author getAuthorEntityFromDTO(Author author) {
        com.epam.jpop.bookservice.entity.Author authorEntity = new com.epam.jpop.bookservice.entity.Author();
        authorEntity.setAuthorCode(author.getCode());
        authorEntity.setAuthorName(author.getName());
        authorEntity.setAuthorAddress(author.getAddress());
        authorEntity.setAuthorPhoneNumber(author.getPhoneNumber());
        return authorEntity;
    }

    private com.epam.jpop.bookservice.entity.Publisher getPublisherEntityFromDTO(Publisher publisher) {
        com.epam.jpop.bookservice.entity.Publisher publisherEntity = new com.epam.jpop.bookservice.entity.Publisher();
        publisherEntity.setPublisherCode(publisher.getCode());
        publisherEntity.setPublisherName(publisher.getName());
        publisherEntity.setPublisherDescription(publisher.getDesc());
        return publisherEntity;
    }

    private com.epam.jpop.bookservice.entity.Category getCategoryEntityFromDTO(Category category) {
        com.epam.jpop.bookservice.entity.Category categoryEntity = new com.epam.jpop.bookservice.entity.Category();
        categoryEntity.setCategoryCode(category.getCode());
        categoryEntity.setCategoryName(category.getName());
        categoryEntity.setCategoryDescription(category.getDesc());
        return categoryEntity;
    }

}
