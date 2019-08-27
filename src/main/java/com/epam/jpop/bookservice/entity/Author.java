package com.epam.jpop.bookservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "author", schema = "books")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "author_cd")
    private String authorCode;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_address")
    private String authorAddress;

    @Column(name = "author_ph_no")
    private String authorPhoneNumber;

}
