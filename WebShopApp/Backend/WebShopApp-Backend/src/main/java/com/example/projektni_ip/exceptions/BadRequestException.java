package com.example.projektni_ip.exceptions;

import org.springframework.http.HttpStatus;

/**
 * When user sends unvalid data then status code is 400
 */
public class BadRequestException extends HttpException{

    public BadRequestException(){
        super(HttpStatus.BAD_REQUEST, null);
    }

    public BadRequestException(Object data){
        super(HttpStatus.BAD_REQUEST, data);
    }
}
