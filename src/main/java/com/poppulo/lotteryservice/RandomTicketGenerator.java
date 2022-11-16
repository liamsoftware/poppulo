package com.poppulo.lotteryservice;


import java.util.Random;

public class RandomTicketGenerator implements TicketGenerator {

    private Random random = new Random();

    public RandomTicketGenerator() {
    }

    @Override
    public Ticket generate(long id, int numberOfLines) {
        Ticket ticket = new Ticket(id);
        while (numberOfLines > 0) {
            ticket.addLine(generateRandomLine());
            numberOfLines--;
        }
        return ticket;
    }

    @Override
    public Ticket amend(Ticket ticket, int numberOfExtraLines) {
        while (numberOfExtraLines > 0) {
            ticket.addLine(generateRandomLine());
            numberOfExtraLines--;
        }
        return ticket;
    }

    private Integer[] generateRandomLine() {
        int r1 = random.nextInt(3);
        int r2 = random.nextInt(3);
        int r3 = random.nextInt(3);
        Integer[] array = {r1, r2, r3};
        return array;
    }
}