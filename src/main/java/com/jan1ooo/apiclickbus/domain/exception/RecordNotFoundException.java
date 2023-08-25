package com.jan1ooo.apiclickbus.domain.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String nome){
        super("Registro não encontrado com o nome: " + nome);
    }
}