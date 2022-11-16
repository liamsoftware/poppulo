package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

public class TicketFormatHelperTest {

    @Mock
    private Ticket ticket;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isValidFormatTest() {
        List<Integer[]> lines = new ArrayList<>();
        Integer[] aLine = new Integer[3];
        aLine[0] = 0;
        aLine[1] = 1;
        aLine[2] = 2;
        lines.add(aLine);
        given(ticket.getLines()).willReturn(lines);
        boolean actual = TicketFormatHelper.isValidFormat(ticket);
        assertTrue(actual);
    }

    @Test
    public void isInvalidFormatTooBigArrayTest() {
        List<Integer[]> lines = new ArrayList<>();
        Integer[] aLine = new Integer[4];
        aLine[0] = 0;
        aLine[1] = 1;
        aLine[2] = 2;
        aLine[3] = 1;
        lines.add(aLine);
        given(ticket.getLines()).willReturn(lines);
        boolean actual = TicketFormatHelper.isValidFormat(ticket);
        assertFalse(actual);
    }

    @Test
    public void isInvalidFormatTooSmallArrayTest() {
        List<Integer[]> lines = new ArrayList<>();
        Integer[] aLine = new Integer[2];
        aLine[0] = 0;
        aLine[1] = 1;
        lines.add(aLine);
        given(ticket.getLines()).willReturn(lines);
        boolean actual = TicketFormatHelper.isValidFormat(ticket);
        assertFalse(actual);
    }

    @Test
    public void isInvalidFormatValueTooBigTest() {
        List<Integer[]> lines = new ArrayList<>();
        Integer[] aLine = new Integer[3];
        aLine[0] = 0;
        aLine[1] = 1;
        aLine[2] = 3;
        lines.add(aLine);
        given(ticket.getLines()).willReturn(lines);
        boolean actual = TicketFormatHelper.isValidFormat(ticket);
        assertFalse(actual);
    }

    @Test
    public void isInvalidFormatValueTooSmallTest() {
        List<Integer[]> lines = new ArrayList<>();
        Integer[] aLine = new Integer[3];
        aLine[0] = 0;
        aLine[1] = -1;
        aLine[2] = 3;
        lines.add(aLine);
        given(ticket.getLines()).willReturn(lines);
        boolean actual = TicketFormatHelper.isValidFormat(ticket);
        assertFalse(actual);
    }
}
