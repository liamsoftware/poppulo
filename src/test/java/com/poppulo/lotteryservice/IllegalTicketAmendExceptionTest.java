package com.poppulo.lotteryservice;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IllegalTicketAmendExceptionTest {

    @Test
    public void ctorTest() {
        Ticket ticket = new Ticket(1L);
        IllegalTicketAmendException illegalTicketAmendException = new IllegalTicketAmendException(ticket);
        assertNotNull(illegalTicketAmendException);
    }
}
