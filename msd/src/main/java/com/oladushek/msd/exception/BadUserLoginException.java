package com.oladushek.msd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BadUserLoginException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public BadUserLoginException(String message){
        super(message);
    }
}
