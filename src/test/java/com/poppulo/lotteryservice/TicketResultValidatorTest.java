package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

public class TicketResultValidatorTest {

    public TicketResultValidator ticketResultValidator;
    @Mock
    private Ticket ticket;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.ticketResultValidator = new TicketResultValidator();
    }

    @Test
    public void getResultTenTest() {

        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {0, 1, 1};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultValidator.getResult(ticket);
        assertEquals("10", r);
    }

    @Test
    public void getResultFiveTest() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {0, 0, 0};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultValidator.getResult(ticket);
        assertEquals("5", r);
    }

    @Test
    public void getResultOneTest() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {0, 1, 2};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultValidator.getResult(ticket);
        assertEquals("1", r);
    }

    @Test
    public void getResultZeroTest() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr = {2, 1, 2};
        list.add(arr);
        given(ticket.getLines()).willReturn(list);

        String r = ticketResultValidator.getResult(ticket);
        assertEquals("0", r);
    }

}