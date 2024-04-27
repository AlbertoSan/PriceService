package com.capitole.app.exception;

public class BrandNotFoundException  extends RuntimeException {

    public BrandNotFoundException(String message) {
        super(message);
    }
}
