package com.poppulo.lotteryservice;

public class IllegalTicketAmendException extends RuntimeException {

    public IllegalTicketAmendException(Ticket ticket) {
        super("Unable to amend a ticket that has already been checked. Ticket: " + ticket);
    }
}
