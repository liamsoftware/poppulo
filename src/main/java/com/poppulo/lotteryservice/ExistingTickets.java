package com.poppulo.lotteryservice;

import java.util.HashMap;
import java.util.Map;

public class ExistingTickets {

    Map<Long, Ticket> tickets = new HashMap<>();

    public boolean addTicket(Ticket ticket) {
        if (!tickets.containsKey(ticket.uniqueId)) {
            tickets.put(ticket.uniqueId, ticket);
            return true;
        }
        return false;
    }

}