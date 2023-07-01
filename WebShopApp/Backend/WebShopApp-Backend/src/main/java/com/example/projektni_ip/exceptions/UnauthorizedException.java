package com.example.projektni_ip.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Return status code 401 in case of authentication
 */
public class UnauthorizedException extends HttpException{

    public UnauthorizedException(){
        super(HttpStatus.UNAUTHORIZED, null);
    }

    public UnauthorizedException(Object data){
        super(HttpStatus.UNAUTHORIZED, data);
    }
}
