package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Stores a cache of generated tickets.
 * Provides ability to add tickets to the cache, get ticket by id from the cache, update a ticket in the cache,
 * and to get a copy of all tickets in the cache.
 */
public class TicketCache {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Long, Ticket> tickets = new HashMap<>();

    public TicketCache() {
    }

    public TicketCache(Map<Long, Ticket> tickets) {
        this.tickets = tickets;
    }

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
        log.info("updateTicket: updating id: {} with ticket [{}].", id, updatedTicket);
        tickets.computeIfPresent(id, (k, v) -> tickets.put(k, updatedTicket));
    }

    public Ticket getTicket(long id) {
        if (!tickets.containsKey(id)) {
            log.info("getTicket: ticket id: {} does not exist in the cache.", id);
        }
        return tickets.get(id);
    }
}