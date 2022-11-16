package com.poppulo.lotteryservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidTicketAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidTicketException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String invalidTicketExceptionHandler(InvalidTicketException e) {
        return e.getMessage();
    }
}
