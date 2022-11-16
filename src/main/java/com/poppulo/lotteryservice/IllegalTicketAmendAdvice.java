package com.poppulo.lotteryservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalTicketAmendAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalTicketAmendException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String illegalTicketAmendExceptionHandler(IllegalTicketAmendException e) {
        return e.getMessage();
    }
}
