package com.epam.jpop.bookservice.controller;

import com.epam.jpop.bookservice.domain.Book;
import com.epam.jpop.bookservice.domain.Result;
import com.epam.jpop.bookservice.exception.BookIdMismatchException;
import com.epam.jpop.bookservice.service.BookService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api(value = "book-service")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping
    @ApiOperation(value = "View list of all available books in the library", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of books"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource requested"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Object> getListOfAllBooks() {
        logger.info("Getting all the available books from the library");
        return new ResponseEntity<>(bookService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a book by an Id")
    public Book getBook(@PathVariable Long id) {
        logger.info("Fetching the book details with the given id: {}", id);
        return bookService.get(id);
    }

    @PostMapping
    @ApiOperation(value = "Add a new book to the library")
    public ResponseEntity<Result> addBook(
            @ApiParam(value = "New Book details object to save the book information to library", required = true) @RequestBody Book book) {
        Result apiResult = bookService.add(book);
        logger.info("Successfully added the given new book to library: {}", apiResult.getId());
        return new ResponseEntity<>(apiResult, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing book from the library")
    public void update(
            @ApiParam(value = "Book Id to Update the details to", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated Book information", required = true) @RequestBody Book book)
            throws BookIdMismatchException {
        logger.info("Updating the book details for an existing book in the library with an ID: {}", id);
        bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an existing book from the library")
    public void delete(
            @ApiParam(value = "Book Id to delete the data from the library", required = true) @PathVariable Long id) {
        logger.info("Deleting the book from the library: {}", id);
        bookService.delete(id);
    }

}
