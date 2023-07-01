package com.example.projektni_ip.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Return 409 status code when user try to duplicate some unique entity
 */
public class ConflictException extends HttpException{

    public ConflictException(){
        super(HttpStatus.CONFLICT, null);
    }

    public ConflictException(Object data){
        super(HttpStatus.CONFLICT, data);
    }
}
