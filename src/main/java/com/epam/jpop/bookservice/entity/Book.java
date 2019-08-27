package com.epam.jpop.bookservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "book")
@SequenceGenerator(name = "book_id_generator", sequenceName = "book_id_seq", allocationSize = 1, initialValue = 1)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_generator")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private Long isbn;

    @ManyToOne
    @JoinColumn(name = "author_cd")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_cd")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "category_cd")
    private Category category;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column
    private Double price;
}
