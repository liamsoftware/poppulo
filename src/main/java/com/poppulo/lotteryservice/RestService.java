package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//should this be split out into put, post, get classes?

@RestController
public class RestService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TicketCache ticketCache = new TicketCache();
    private TicketGenerator ticketGenerator = new RandomTicketGenerator();
    private RulePolicy rulePolicy = new SimpleRulePolicy();

    public RestService() {
    }

    public RestService(TicketCache ticketCache, TicketGenerator ticketGenerator, RulePolicy rulePolicy) {
        this.ticketCache = ticketCache;
        this.ticketGenerator = ticketGenerator;
        this.rulePolicy = rulePolicy;
    }

    @PostMapping(value = "/ticket")
    public Ticket createTicket() {
        long id = ticketCache.getNextId();
        Ticket createdTicket = ticketGenerator.generate(id, 0);
        ticketCache.addTicket(createdTicket);
        log.info("createTicket: created new ticket: [{}]", id);
        return createdTicket;
    }

    @GetMapping(value = "/ticket/{id}")
    public Ticket getTicket(@PathVariable long id) {
        log.info("get ticket by id. id: {}", id);
        Ticket ticket = ticketCache.getTicket(id);
        if (ticket != null) {
            return ticket;
        }
        throw new TicketNotFoundException(id);
    }

    @GetMapping(value = "/ticket")
    public List<Ticket> getAllTickets() {
        List<Ticket> allTickets = ticketCache.getAllTickets();
        log.info("getAllTickets: {} ticket(s) are stored.", allTickets.size());
        return allTickets;
    }

    @PutMapping(value = "/ticket/{id}/{numberOfAdditionalLines}")
    public Ticket amendTicket(@PathVariable long id, @PathVariable int numberOfAdditionalLines) {
        log.info("amendTicket: id:{} numLines:{}", id, numberOfAdditionalLines);
        Ticket ticketToAmend = ticketCache.getTicket(id);

        if (ticketToAmend == null) {
            log.info("amendTicket: ticket with id: {} not found in cache.", id);
            throw new TicketNotFoundException(id);
        }

        if (numberOfAdditionalLines <= 0) {
            log.info("amendTicket: cannot amend {} number of lines on a ticket.", numberOfAdditionalLines);
            return ticketToAmend;
        }

        return amendTicket(ticketToAmend, numberOfAdditionalLines);
    }

    private Ticket amendTicket(Ticket ticketToAmend, int numberOfAdditionalLines) {
        log.info("amendTicket: ticket to amend: {}", ticketToAmend);
        Ticket amendedTicked = ticketGenerator.amend(ticketToAmend, numberOfAdditionalLines);
        ticketCache.updateTicket(amendedTicked.getUniqueId(), amendedTicked);
        log.info("amendTicket: amended ticket: {}", amendedTicked);
        return amendedTicked;
    }

    @GetMapping(value = "/status/{id}")
    public String getTicketStatus(@PathVariable long id) {
        Ticket ticketToValidate = ticketCache.getTicket(id);
        if (ticketToValidate != null) {
            String ticketStatus = rulePolicy.computeResult(ticketToValidate);
            log.info("getTicketStatus: id: {}, status: {}", id, ticketStatus);
            return ticketStatus;
        }
        throw new TicketNotFoundException(id);
    }

    @PostMapping(value = "/status")
    public String getTicketStatus(@RequestBody Ticket ticket) {
        if (TicketCheckHelper.isValidFormat(ticket)) {
            log.info("getTicketStatus: ticket to check: [{}]", ticket);
            return rulePolicy.computeResult(ticket);
        }
        throw new InvalidTicketException();
    }

    //allow for creating a ticket with an id as a param, then you'll have to check the cache for existence...
}