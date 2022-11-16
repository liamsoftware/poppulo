package com.poppulo.lotteryservice;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TicketNotFoundExceptionTest {

    @Test
    public void ctorTest() {
        TicketNotFoundException ticketNotFoundException = new TicketNotFoundException(1L);
        assertNotNull(ticketNotFoundException);
    }
}
