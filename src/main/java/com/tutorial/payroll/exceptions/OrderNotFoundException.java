package com.tutorial.payroll.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id) {
        super("Could not found order " + id);
    }
}
