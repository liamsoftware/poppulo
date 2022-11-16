package com.poppulo.lotteryservice;

public class InvalidTicketException extends RuntimeException {

    public InvalidTicketException() {
        super("Ticket format is invalid and will not be checked.");
    }
}
