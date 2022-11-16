package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TicketCacheTest {

    private TicketCache ticketCache;
    private Map<Long, Ticket> tickets = new HashMap<>();

    @Before
    public void setup() {
        ticketCache = new TicketCache(tickets);
    }

    @Test
    public void addValidTicketTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.addTicket(ticket);
        assertTrue(tickets.containsKey(aTicketId));
    }

    @Test
    public void addExistingTicketTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.addTicket(ticket);
        assertFalse(ticketCache.addTicket(ticket));
    }

    @Test
    public void getNextIdWhenCacheIsEmptyTest() {
        assertEquals(1L, ticketCache.getNextId());
    }

    @Test
    public void getNextIdWhenCacheContainsTicketsTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.addTicket(ticket);
        assertTrue(tickets.containsKey(aTicketId));
        assertEquals(2L, ticketCache.getNextId());
    }

    @Test
    public void doesExistTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.addTicket(ticket);
        assertTrue(ticketCache.doesExist(aTicketId));
    }

    @Test
    public void doesNotExistTest() {
        long aTicketId = 1L;
        assertFalse(ticketCache.doesExist(aTicketId));
    }

    @Test
    public void updateExistingTicketTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.addTicket(ticket);
        ticket.addLine(new Integer[]{0,1,1});
        ticketCache.updateTicket(aTicketId, ticket);
        Ticket updated = tickets.get(aTicketId);
        assertEquals(1, updated.getNumberOfLines());
    }

    @Test
    public void updateNonExistingTicketTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.updateTicket(aTicketId, ticket);
        assertEquals(0, tickets.size());
    }

    @Test
    public void getExistingTicketTest() {
        long aTicketId = 1L;
        Ticket ticket = new Ticket(aTicketId);
        ticketCache.addTicket(ticket);
        Ticket anExistingTicket = ticketCache.getTicket(aTicketId);
        assertNotNull(anExistingTicket);
    }

    @Test
    public void getNonExistingTicketTest() {
        long aNonExistingTicketId = 99;
        assertNull(ticketCache.getTicket(aNonExistingTicketId));
    }
}