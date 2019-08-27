package com.epam.jpop.bookservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "category", schema = "books")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_cd")
    private String categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_desc")
    private String categoryDescription;

}
