package com.poppulo.lotteryservice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TicketNotFoundAdviceTest {

    @Test
    public void ticketNotFoundExceptionHandlerTest() {
        TicketNotFoundAdvice ticketNotFoundAdvice = new TicketNotFoundAdvice();
        TicketNotFoundException ticketNotFoundException = new TicketNotFoundException(1L);
        String message = ticketNotFoundAdvice.ticketNotFoundExceptionHandler(ticketNotFoundException);
        assertFalse(message.isEmpty());
    }
}
