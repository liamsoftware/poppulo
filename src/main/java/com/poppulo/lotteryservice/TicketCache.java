package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TicketCache {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<Long, Ticket> tickets = new HashMap<>();

    public boolean addTicket(Ticket ticket) {
        if (!tickets.containsKey(ticket.getUniqueId())) {
            tickets.put(ticket.getUniqueId(), ticket);
            log.info("addTicket: added ticket [{}] to cache.", ticket);
            return true;
        }
        log.warn("addTicket: failed to add ticket [{}] to cache. Ticket already exists.", ticket);
        return false;
    }

    public boolean doesExist(long id) {
        return tickets.containsKey(id);
    }

    public long getNextId() {
        if (tickets.isEmpty()) return 1L;
        long nextId = Collections.max(tickets.keySet()) + 1;
        log.info("getNextId: next id: {}.", nextId);
        return nextId;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> ticketsCopy = new ArrayList<>(tickets.values());
        log.debug("getAllTickets: copy of tickets size: {}.", ticketsCopy.size());
        return ticketsCopy;
    }

    public void updateTicket(long id, Ticket updatedTicket) {
        if (tickets.containsKey(id)) {
            Ticket currentTicket = tickets.get(id);
            log.info("updateTicket: updating ticket from [{}], to [{}]", currentTicket, updatedTicket);
            tickets.put(id, updatedTicket);
        } else {
            log.warn("updatedTicket: ticket: [{}] does not exist in the cache.", updatedTicket);
//            throw new RuntimeException();
        }
    }

    public Ticket getTicket(long id) {
        if (!tickets.containsKey(id)) {
            log.warn("getTicket: ticket id: {} does not exist in the cache.", id);
        }
        Ticket ticket = tickets.get(id);
        return ticket;
    }
}