package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RestService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    TicketCache ticketCache = new TicketCache();
    RandomTicketGenerator randomTicketGenerator = new RandomTicketGenerator();
    TicketResultValidator ticketResultValidator = new TicketResultValidator();

    public RestService(TicketCache ticketCache, RandomTicketGenerator randomTicketGenerator, TicketResultValidator ticketResultValidator) {
        this.ticketCache = ticketCache;
        this.randomTicketGenerator = randomTicketGenerator;
        this.ticketResultValidator = ticketResultValidator;
    }

    public RestService() {
    }

    @PostMapping(value = "/ticket")
    public Ticket createTicket() {
        long id = ticketCache.getNextId();
        Ticket createdTicket = randomTicketGenerator.generate(id, 0);
        if (ticketCache.addTicket(createdTicket)) {
            log.info("createTicket: created with id: {}", id);
            return createdTicket;
        }
        return null;
    }

    @GetMapping(value = "/ticket/{id}")
    public Ticket getTicket(@PathVariable long id) {
        log.info("get ticket by id. id: {}", id);
        //validate input... and get ticket...
        return new Ticket();
    }

    @GetMapping(value = "/ticket")
    public Collection<Ticket> getAllTickets() {
        Collection<Ticket> allTickets = ticketCache.getAllTickets();
        if (allTickets != null) {
            log.info("getAllTickets: {} ticket(s) are stored.", allTickets.size());
            return allTickets;
        }
        log.warn("getAllTickets: there are no tickets stored."); //clean this up, should return empty list...
        return null;
    }

    @PutMapping(value = "/ticket/{id}/{numberOfAdditionalLines}")
    public Ticket amendTicket(@PathVariable long id, @PathVariable int numberOfAdditionalLines) {
        log.info("amendTicket: id:{} numLines:{}", id, numberOfAdditionalLines);
        if (numberOfAdditionalLines > 0) {
            Ticket ticketToAmend = ticketCache.getTicket(id);
            log.info("amendTicket: ticket to amend: {}", ticketToAmend);
            if (ticketToAmend != null) {
                Ticket amendedTicked = randomTicketGenerator.amend(ticketToAmend, numberOfAdditionalLines);
                ticketCache.updateTicket(id, amendedTicked);
                log.info("amendTicket: amended ticket: {}", amendedTicked);
                return amendedTicked;
            }
        }
        return null;
    }

    @GetMapping(value = "/status/{id}")
    public String getTicketStatus(@PathVariable long id) {
        Ticket ticketToValidate = ticketCache.getTicket(id);
        if (ticketToValidate != null) {
            String ticketStatus = ticketResultValidator.getResult(ticketToValidate);
            log.info("getTicketStatus: id: {}, status: {}", id, ticketStatus);
            return ticketStatus;
        }
        return null;
    }

    //allow for checking a ticket status by passing the ticket into the request.
}