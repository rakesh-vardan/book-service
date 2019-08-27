package com.epam.jpop.bookservice.domain;

import lombok.Data;

@Data
public class Author {
    private String code;
    private String name;
    private String address;
    private String phoneNumber;
}
