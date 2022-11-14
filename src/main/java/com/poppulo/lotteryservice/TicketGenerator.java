package com.poppulo.lotteryservice;

import java.util.Random;

public class TicketGenerator {

    //if the id is not in the cache, then create new ticket with x lines
    private ExistingTickets existingTickets;
    private Random random = new Random();

    public TicketGenerator(ExistingTickets existingTickets) {
        this.existingTickets = existingTickets;
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

    private boolean doesTicketAlreadyExist(long id) {
        return existingTickets.doesExist(id);
    }

    private Integer[] generateRandomLine() {
        int r1 = random.nextInt(3);
        int r2 = random.nextInt(3);
        int r3 = random.nextInt(3);
        Integer[] array = {r1, r2, r3};
        return array;
    }

}