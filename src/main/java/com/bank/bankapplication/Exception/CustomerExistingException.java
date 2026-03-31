package com.bank.bankapplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerExistingException extends RuntimeException{

    public CustomerExistingException(String msg){
        super(msg);
    }
}
