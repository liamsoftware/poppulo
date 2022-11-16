package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

public class RestServiceTest {

    private RestService restService;
    private final Ticket ticket = new Ticket();
    private final List<Ticket> tickets = new ArrayList<>();
    @Mock
    private TicketCache ticketCache;
    @Mock
    private RandomTicketGenerator randomTicketGenerator;
    @Mock
    private SimpleRulePolicy simpleRulePolicy;
    @Mock
    private Ticket ticketMock;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        restService = new RestService(ticketCache, randomTicketGenerator, simpleRulePolicy);
        tickets.add(ticket);
    }

    @Test
    public void createTicketTest() {
        given(ticketCache.getNextId()).willReturn(1L);
        given(randomTicketGenerator.generate(anyLong(), anyInt())).willReturn(ticket);
        given(ticketCache.addTicket(ticket)).willReturn(true);
        Ticket createdTicket = restService.createTicket();
        assertEquals(ticket, createdTicket);
    }

    @Test
    public void getAllTicketsTest() {
        given(ticketCache.getAllTickets())
                .willReturn(tickets);
        List<Ticket> allTickets = restService.getAllTickets();
        assertEquals(allTickets, tickets);
    }

    @Test
    public void amendTicketTest() {
        Ticket t = new Ticket(1L);
        Ticket updatedTicket = new Ticket(1L);
        updatedTicket.addLine(new Integer[3]);
        updatedTicket.addLine(new Integer[3]);

        given(ticketCache.getTicket(1L)).willReturn(t);
        given(randomTicketGenerator.amend(any(Ticket.class), anyInt())).willReturn(updatedTicket);

        Ticket amendedTicket = restService.amendTicket(1L, 2);

        assertEquals(2, amendedTicket.getLines().size());
    }

    @Test
    public void amendTicketNoLinesTest() {
        Ticket t = new Ticket(1L);
        given(ticketCache.getTicket(1L)).willReturn(t);
        Ticket amendedTicket = restService.amendTicket(1L, -1);
        assertEquals(0, amendedTicket.getNumberOfLines());
    }

    @Test(expected = IllegalTicketAmendException.class)
    public void amendTicketAlreadyCheckedTest() {
        Ticket t = new Ticket(1L);
        t.setChecked();
        given(ticketCache.getTicket(1L)).willReturn(t);
        restService.amendTicket(1L, -1);
    }

    @Test
    public void getTicketStatusTest() {
        given(ticketCache.getTicket(anyLong())).willReturn(ticketMock);
        given(ticketMock.getResults()).willReturn(new ArrayList<>());
        given(simpleRulePolicy.computeResult(any(Ticket.class))).willReturn(ticketMock);
        Ticket result = restService.getTicketStatus(3L);
        assertEquals(0, result.getResults().size());
    }

    @Test
    public void getTicketTest() {
        given(ticketCache.getTicket(anyLong())).willReturn(ticket);
        Ticket returnedTicket = restService.getTicket(1L);
        assertEquals(ticket, returnedTicket);
    }

    @Test(expected = TicketNotFoundException.class)
    public void getTicketThatDoesNotExistTest() {
        restService.getTicket(1L);
    }

    @Test
    public void ctorTest() {
        RestService anotherRestService = new RestService();
        assertNotNull(anotherRestService);
    }
}