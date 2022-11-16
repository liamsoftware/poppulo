package com.poppulo.lotteryservice;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(long id) {
        super("Could not find ticket with id: " + id);
    }
}
