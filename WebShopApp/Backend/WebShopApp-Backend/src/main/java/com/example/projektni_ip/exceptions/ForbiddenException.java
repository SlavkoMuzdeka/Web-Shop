package com.example.projektni_ip.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Return status code 403 if user doens't have permission to do some action
 */
public class ForbiddenException extends HttpException{

    public ForbiddenException(){
        super(HttpStatus.FORBIDDEN, null);
    }

    public ForbiddenException(Object data){
        super(HttpStatus.FORBIDDEN, data);
    }
}
