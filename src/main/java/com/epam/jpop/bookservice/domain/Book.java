package com.epam.jpop.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
    private Long isbn;
    private String publisher;
    private Integer publishedYear;
}
