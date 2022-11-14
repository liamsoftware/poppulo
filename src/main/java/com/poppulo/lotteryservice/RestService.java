package com.poppulo.lotteryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestService {

    //return a list of tickets. (What if there are a lot of tickets??)
    @PostMapping
    public List<Ticket> ticket() {
        return null;
    }

    //gets an individual ticket
    @GetMapping
    public Ticket singleTicket(long id) {
        return null;
    }

    //amends a ticket, adding x additional lines
    @PutMapping
    public boolean amendTicket(long id, int numberOfAdditionalLines) {
        return false;
    }

    //retrieves the status of a ticket
    @PutMapping
    public String retrieveTicketStatus(long id) {
        return null;
    }
}