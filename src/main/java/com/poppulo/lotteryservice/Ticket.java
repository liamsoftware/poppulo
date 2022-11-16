package com.poppulo.lotteryservice;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a lottery ticket.
 * Has unique ID and list of lines.
 */
public class Ticket {

    private long uniqueId;
    private List<Integer[]> lines = new ArrayList<>();

    public Ticket(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Ticket() {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(getUniqueId())
                .append(" number of lines: ").append(getNumberOfLines());
        return sb.toString();
    }
}