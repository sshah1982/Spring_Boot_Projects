package com.demo.crud.util;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseEntity<T> extends ResponseEntity<Object> implements Serializable {

    private static final long serialVersionUID = 7156526077883281625L;

    public CustomResponseEntity(HttpStatus status) {
        super(status);
    }

    public CustomResponseEntity(Object body, HttpStatus status) {
        super(body, status);
    }
}