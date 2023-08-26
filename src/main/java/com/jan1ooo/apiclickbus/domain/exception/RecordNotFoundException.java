package com.jan1ooo.apiclickbus.domain.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String msg){
        super("No user with this name: " + msg);
    }
}