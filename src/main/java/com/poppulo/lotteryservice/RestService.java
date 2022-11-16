package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RestService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    TicketCache ticketCache = new TicketCache();
    TicketGenerator ticketGenerator = new TicketGenerator(ticketCache);
    TicketResultValidator ticketResultValidator = new TicketResultValidator();

    public RestService(TicketCache ticketCache, TicketGenerator ticketGenerator, TicketResultValidator ticketResultValidator) {
        this.ticketCache = ticketCache;
        this.ticketGenerator = ticketGenerator;
        this.ticketResultValidator = ticketResultValidator;
    }

    public RestService() {
    }

    @PostMapping
    public Ticket createTicket() {
        long id = ticketCache.getNextId();
        Ticket createdTicket = ticketGenerator.generateTicket(id, 0);
        if (ticketCache.addTicket(createdTicket)) {
            log.info("createTicket: created with id: {}", id);
            return createdTicket;
        }
        return null;
    }

    //return a list of tickets. (What if there are a lot of tickets??)
    @GetMapping
    public Collection<Ticket> getAllTickets() {
        Collection<Ticket> allTickets = ticketCache.getAllTickets();
        if (allTickets != null) {
            log.info("getAllTickets: {} ticket(s) are stored.", allTickets.size());
            return allTickets;
        }
        log.warn("getAllTickets: there are no tickets stored."); //clean this up, should return empty list...
        return null;
    }

    //gets an individual ticket
//    @GetMapping
//    public Ticket amendTicket(long id) {
//        return null;
//    }

    //amends a ticket, adding x additional lines
    @PutMapping
    public Ticket amendTicket(long id, int numberOfAdditionalLines) {
        log.info("amendTicket: id:{} numLines:{}", id, numberOfAdditionalLines);
        if (numberOfAdditionalLines > 0) {
            Ticket ticketToAmend = ticketCache.getTicket(id);
            log.info("amendTicket: ticket to amend: {}", ticketToAmend);
            if (ticketToAmend != null) {
                Ticket amendedTicked = ticketGenerator.amendTicket(ticketToAmend, numberOfAdditionalLines);
                ticketCache.updateTicket(id, amendedTicked);
                log.info("amendTicket: amended ticket: {}", amendedTicked);
                return amendedTicked;
            }
        }
        return null;
    }

    //retrieves the status of a ticket
    @PutMapping
    public String getTicketStatus(long id) {
        Ticket ticketToValidate = ticketCache.getTicket(id);
        if (ticketToValidate != null) {
            String ticketStatus = ticketResultValidator.getResult(ticketToValidate);
            log.info("getTicketStatus: id: {}, status: {}", id, ticketStatus);
            return ticketStatus;
        }
        return null;
    }
}