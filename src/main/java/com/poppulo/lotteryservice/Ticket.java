package com.poppulo.lotteryservice;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private long uniqueId;
    private List<Integer[]> lines = new ArrayList<>();

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

    public long getUniqueId() {
        return this.uniqueId;
    }

    public void addLine(Integer[] line) {
        lines.add(line);
    }

    public int getNumberOfLines() {
        return lines.size();
    }
}