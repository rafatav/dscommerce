package com.devsuperior.dscommerce.exceptions;

public class DatabaseNotFound extends RuntimeException{

    public DatabaseNotFound(String msg) {
        super(msg);
    }
}
