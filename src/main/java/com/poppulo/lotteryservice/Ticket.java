package com.poppulo.lotteryservice;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    long uniqueId;
    List<Integer[]> lines = new ArrayList<>();

    public Ticket(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public boolean generateTicket(int numberOfLines) {
        return false;
    }

    public boolean addLines(int numberOfLinesToAdd) {
        return false;
    }

    public List<Integer[]> getLines() {
        return lines;
    }
}