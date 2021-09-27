package com.exchange.andevisbackend.exceptions;

public class NoCurrencyDataException extends RuntimeException{

    public NoCurrencyDataException (String message){
        super(message);
    }
}
