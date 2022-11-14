package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

public class TicketResultCheckerTest {

    public TicketResultChecker ticketResultChecker;
    @Mock
    private Ticket ticket;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.ticketResultChecker = new TicketResultChecker();
    }

    @Test
    public void getResultTenTest() {

        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {0, 1, 1};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultChecker.getResult(ticket);
        assertTrue(r.equals("10"));
    }

    @Test
    public void getResultFiveTest() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {0, 0, 0};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultChecker.getResult(ticket);
        assertTrue(r.equals("5"));
    }

    @Test
    public void getResultOneTest() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {0, 1, 2};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultChecker.getResult(ticket);
        assertTrue(r.equals("1"));
    }

    @Test
    public void getResultZeroTest() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {2, 1, 2};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultChecker.getResult(ticket);
        assertTrue(r.equals("0"));
    }

}