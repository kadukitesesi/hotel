package com.kadukitesesi.hotel.api.exception;

public class QuartoIndisponivelException extends RuntimeException{

    public QuartoIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
