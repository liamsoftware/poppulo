package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SimpleRulePolicyTest {

    public SimpleRulePolicy simpleRulePolicy;
    @Mock
    private Ticket ticket;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.simpleRulePolicy = new SimpleRulePolicy();
    }

    @Test
    public void setTicketCheckFlagWhenNotCheckedTest() {
        given(ticket.isResultChecked()).willReturn(false);
        simpleRulePolicy.computeResult(ticket);
        verify(ticket, times(1)).setChecked();
    }

    @Test
    public void getResultTenTest() {
        Ticket ticket = new Ticket(1L);
        Integer[] arr = {0, 1, 1};
        ticket.addLine(arr);
        Ticket resultTicket = simpleRulePolicy.computeResult(ticket);
        LineResult lineResult = resultTicket.getResults().get(0);
        assertEquals(10, lineResult.getResult());
    }

    @Test
    public void getResultFiveTest() {
        Ticket ticket = new Ticket(1L);
        Integer[] arr = {0, 0, 0};
        ticket.addLine(arr);
        Ticket resultTicket = simpleRulePolicy.computeResult(ticket);
        LineResult lineResult = resultTicket.getResults().get(0);
        assertEquals(5, lineResult.getResult());
    }

    @Test
    public void getResultOneTest() {
        Ticket ticket = new Ticket(1L);
        Integer[] arr = {0, 2, 2};
        ticket.addLine(arr);
        Ticket resultTicket = simpleRulePolicy.computeResult(ticket);
        LineResult lineResult = resultTicket.getResults().get(0);
        assertEquals(1, lineResult.getResult());
    }

    @Test
    public void getResultZeroTest() {
        Ticket ticket = new Ticket(1L);
        Integer[] arr = {1, 2, 1};
        ticket.addLine(arr);
        Ticket resultTicket = simpleRulePolicy.computeResult(ticket);
        LineResult lineResult = resultTicket.getResults().get(0);
        assertEquals(0, lineResult.getResult());
    }

    @Test
    public void getResultZeroSecondTest() {
        Ticket ticket = new Ticket(1L);
        Integer[] arr = {2, 0, 2};
        ticket.addLine(arr);
        Ticket resultTicket = simpleRulePolicy.computeResult(ticket);
        LineResult lineResult = resultTicket.getResults().get(0);
        assertEquals(0, lineResult.getResult());
    }

    @Test
    public void returnOriginalTicketWithoutUpdateWhenCheckFlagIsSet() {
        Ticket ticket = new Ticket(1L);
        ticket.setChecked();
        Ticket returnedTicket = simpleRulePolicy.computeResult(ticket);
        assertEquals(ticket, returnedTicket);
    }
}