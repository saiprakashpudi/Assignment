package com.caw.exceptions;

public class CustomRunTimeException extends RuntimeException{


    public CustomRunTimeException(Exception e){
        super(e);
    }

    public CustomRunTimeException(String message){
        super(message);
    }
}
