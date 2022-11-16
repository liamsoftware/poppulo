package com.poppulo.lotteryservice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class IllegalTicketAmendAdviceTest {

    @Test
    public void illegalTicketAmendExceptionHandlerTest() {
        Ticket ticket = new Ticket(1L);
        IllegalTicketAmendException illegalTicketAmendException = new IllegalTicketAmendException(ticket);
        IllegalTicketAmendAdvice illegalTicketAmendAdvice = new IllegalTicketAmendAdvice();
        String message = illegalTicketAmendAdvice.illegalTicketAmendExceptionHandler(illegalTicketAmendException);
        assertFalse(message.isEmpty());
    }
}
