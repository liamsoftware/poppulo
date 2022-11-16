package com.poppulo.lotteryservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Generates lottery lines of random values between 0 and 2, with 3 values for each line.
 * Creating a ticket takes an id and the number of lines to add to the ticket.
 * Amending a ticket takes a ticket and the number of extra lines to tadd to the ticket.
 */
public class RandomTicketGenerator implements TicketGenerator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int BOUND = 3;
    private static final int ZERO_LINES = 0;

    private Random random = new Random();

    public RandomTicketGenerator() {
    }

    @Override
    public Ticket generate(long id, int numberOfLines) {
        log.info("generate: creating ticket with id {} and {} line(s).", id, numberOfLines);
        Ticket ticket = new Ticket(id);
        return addLines(ticket, numberOfLines);
    }

    @Override
    public Ticket amend(Ticket ticket, int numberOfExtraLines) {
        log.info("amend: amending ticket with id {} and {} additional line(s).", ticket.getUniqueId(),
                numberOfExtraLines);
        return addLines(ticket, numberOfExtraLines);
    }

    private Ticket addLines(Ticket ticket, int numberOfLines) {
        while (numberOfLines > ZERO_LINES) {
            ticket.addLine(generateRandomLine());
            numberOfLines--;
        }
        return ticket;
    }

    private Integer[] generateRandomLine() {
        int randomIntOne = random.nextInt(BOUND);
        int randomIntTwo = random.nextInt(BOUND);
        int randomIntThree = random.nextInt(BOUND);
        Integer[] array = {randomIntOne, randomIntTwo, randomIntThree};
        return array;
    }
}