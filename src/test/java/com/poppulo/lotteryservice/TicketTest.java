package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    private Ticket ticket;

    @Before
    public void setup() {
        ticket = new Ticket(1L);
    }

    @Test
    public void addLineTest() {
        ticket.addLine(new Integer[]{0,1,1});
        assertEquals(1, ticket.getNumberOfLines());
    }

    @Test
    public void sortResultTest() {
        ticket.addResult(new Result(1, "[0,1,2]"));
        ticket.addResult(new Result(10, "[0,1,1]"));
        ticket.addResult(new Result(0, "[1,1,2]"));
        ticket.addResult(new Result(5, "[0,0,0]"));
        ticket.sortResults();
        List<Result> results = ticket.getResults();
        assertEquals(10, results.get(0).getScore());
    }
}