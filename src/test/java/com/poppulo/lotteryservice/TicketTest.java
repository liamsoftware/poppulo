package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TicketTest {

    private Ticket ticket;

    @Before
    public void setup() {
        ticket = new Ticket(1L);
    }

    @Test
    public void sortResultTest() {
        ticket.addLineResult("1", "[0,1,2]");
        ticket.addLineResult("10", "[0,1,1]");
        ticket.addLineResult("0", "[1,1,2]");
        ticket.addLineResult("5", "[0,0,0]");
        ticket.sortResults();
        List<String> results = ticket.getResults();
        for (String s : results) System.out.println(s);
        assertTrue(results.get(0).contains("Result 10."));

    }
}