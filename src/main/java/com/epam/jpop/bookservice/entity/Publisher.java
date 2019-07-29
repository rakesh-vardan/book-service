package com.epam.jpop.bookservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "publisher", schema = "books")
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "publisher_cd")
    private String publisherCode;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "publisher_desc")
    private String publisherDescription;

}
