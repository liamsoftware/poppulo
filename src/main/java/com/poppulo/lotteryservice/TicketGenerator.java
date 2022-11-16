package com.poppulo.lotteryservice;

public interface TicketGenerator {

    Ticket generate(long id, int numberOfLines);
    Ticket amend(Ticket ticket, int numberOfAdditionalLines);
}
