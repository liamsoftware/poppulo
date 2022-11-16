package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class IntegrationTest {

    private TicketCache ticketCache;
    private RandomTicketGenerator randomTicketGenerator;
    private SimpleRulePolicy simpleRulePolicy;

    private RestService restService;

    @Before
    public void setup() {
        ticketCache = new TicketCache();
        randomTicketGenerator = new RandomTicketGenerator();
        simpleRulePolicy = new SimpleRulePolicy();
        restService = new RestService(ticketCache, randomTicketGenerator, simpleRulePolicy);
    }

    @Test
    public void createTicket() {
        Ticket createdTicket = restService.createTicket();
        assertEquals(1L, createdTicket.getUniqueId());
    }

    @Test
    public void getAllTickets() {
        restService.createTicket();
        restService.createTicket();
        Collection<Ticket> tickets = restService.getAllTickets();
        assertEquals(2, tickets.size());
    }

    @Test
    public void amendExistingTicket() {
        restService.createTicket();
        Collection<Ticket> tickets = restService.getAllTickets();
        tickets.stream().forEach(ticket -> {
            restService.amendTicket(ticket.getUniqueId(), 2);
        });
        Collection<Ticket> updatedTickets = restService.getAllTickets();
        updatedTickets.stream().forEach(ticket -> assertEquals(2, ticket.getLines().size()));
    }

    @Test
    public void amendNonExistingTicket() {
        Ticket t = restService.amendTicket(100L, 50);
        assertNull(t);
    }

    @Test
    public void getTicketStatusOfExistingTicketTest() {
        restService.createTicket();
        Collection<Ticket> tickets = restService.getAllTickets();
        tickets.stream().forEach(ticket -> {
            String status = restService.getTicketStatus(ticket.getUniqueId());
            assertFalse(status.isBlank());
        });
    }

    @Test
    public void getTicketStatusOfNonExistingTicketTest() {
        String status = restService.getTicketStatus(100L);
        assertNull(status);
    }

}
