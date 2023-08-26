package com.jan1ooo.apiclickbus.domain.exception;

public class BodyBadRequestException extends RuntimeException{

    public BodyBadRequestException(String msg){
        super(msg);
    }

}