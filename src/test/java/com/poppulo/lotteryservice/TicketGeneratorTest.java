package com.poppulo.lotteryservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class TicketGeneratorTest {

    private TicketGenerator ticketGenerator;

    @Mock
    private ExistingTickets existingTickets;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.ticketGenerator = new TicketGenerator(existingTickets);
    }

    @Test
    public void generateTicketWithNewIdTest() {
        given(existingTickets.doesExist(anyLong())).willReturn(false);

        Ticket newTicket = ticketGenerator.generateTicket(1L, 5);
        assertTrue(newTicket.getNumberOfLines() == 5);
    }

    @Test
    public void generateTicketLinesExistWithValidNumbers() {
        given(existingTickets.doesExist(anyLong())).willReturn(false);

        Ticket newTicket = ticketGenerator.generateTicket(1L, 50);
        List<Integer[]> lines = newTicket.getLines();
        for (Integer[] arr : lines) {
            for (int i : arr) {
                assertTrue(i == 0 || i == 1 || i ==2);
            }
        }
    }
}