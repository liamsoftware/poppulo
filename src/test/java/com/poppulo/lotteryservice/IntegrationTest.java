package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.poppulo.lotteryservice.SimpleRulePolicy.TICKET_CONTAINS_SUM_OF_TWO;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

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

    @Test(expected = TicketNotFoundException.class)
    public void amendNonExistingTicket() {
        restService.amendTicket(100L, 50);
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

    @Test(expected = TicketNotFoundException.class)
    public void getTicketStatusOfNonExistingTicketTest() {
        restService.getTicketStatus(100L);
    }

    @Test
    public void getTicketStatusInjectedTicketTest() {
        Integer[] line = new Integer[3];
        line[0] = 1;
        line[1] = 0;
        line[2] = 1;
        Ticket ticket = new Ticket(1L);
        ticket.addLine(line);
        String result = restService.getTicketStatus(ticket);
        assertEquals(TICKET_CONTAINS_SUM_OF_TWO, result);
    }

    @Test(expected = RuntimeException.class)
    public void getTicketStatusInjectedInvalidTicketTest() {
        Integer[] line = new Integer[2];
        line[0] = 1;
        line[2] = 1;
        Ticket ticket = new Ticket(1L);
        ticket.addLine(line);
        restService.getTicketStatus(ticket);
    }

}
