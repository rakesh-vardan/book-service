package com.epam.jpop.bookservice.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

}
