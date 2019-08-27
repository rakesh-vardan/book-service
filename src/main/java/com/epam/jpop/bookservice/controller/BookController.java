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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/books")
@Api(value = "book-service")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping
    @ApiOperation(value = "View list of available books", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Object> list() {
        logger.info("Getting all the available books");
        return new ResponseEntity<>(bookService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a book by Id")
    public Book getBook(@PathVariable Long id) {
        logger.info("Fetching the book details with id: {}", id);
        return bookService.show(id);
    }

    @PostMapping
    @ApiOperation(value = "Add a new book")
    public ResponseEntity<Result> save(
            @ApiParam(value = "New Book details object to save the data", required = true) @RequestBody Book book) {
        Result apiResult = bookService.add(book);
        logger.info("Successfully added a new book: {}", apiResult.getId());
        return new ResponseEntity<>(apiResult, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing book")
    public void update(
            @ApiParam(value = "Book Id to Update the details", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated Book object", required = true) @Valid @RequestBody Book book)
            throws BookIdMismatchException {
        logger.info("Updating the book details for book ID: {}", id);
        bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an existing book")
    public void delete(
            @ApiParam(value = "Book Id to delete the data", required = true) @PathVariable Long id) {
        logger.info("Deleting the book from the system: {}", id);
        bookService.delete(id);
    }

}
