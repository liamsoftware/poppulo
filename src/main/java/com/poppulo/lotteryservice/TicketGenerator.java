package com.poppulo.lotteryservice;

import java.util.Random;

public class TicketGenerator {

    //if the id is not in the cache, then create new ticket with x lines
    private TicketCache ticketCache;
    private Random random = new Random();

    public TicketGenerator(TicketCache ticketCache) {
        this.ticketCache = ticketCache;
    }

    public Ticket generateTicket(long id, int numberOfLines) {
        if (!doesTicketAlreadyExist(id)) {
            Ticket ticket = new Ticket(id);
            while (numberOfLines > 0) {
                ticket.addLine(generateRandomLine());
                numberOfLines--;
            }
            return ticket;
        }
        return null;
    }

    public Ticket amendTicket(Ticket ticket, int numberOfExtraLines) {
        while (numberOfExtraLines > 0) {
            ticket.addLine(generateRandomLine());
            numberOfExtraLines--;
        }
        return ticket;
    }

    private boolean doesTicketAlreadyExist(long id) {
        return ticketCache.doesExist(id);
    }

    private Integer[] generateRandomLine() {
        int r1 = random.nextInt(3);
        int r2 = random.nextInt(3);
        int r3 = random.nextInt(3);
        Integer[] array = {r1, r2, r3};
        return array;
    }

}