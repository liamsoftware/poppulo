package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a lottery ticket.
 * Has unique ID and list of lines.
 */
public class Ticket {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private long uniqueId;
    private List<Integer[]> lines = new ArrayList<>();
    private boolean resultChecked = false;
    private List<String> results =  new ArrayList<>();

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

    public void setChecked() {
        resultChecked = true;
    }

    public boolean isResultChecked() {
        return resultChecked; //check in case pass by reference or pass by value.
    }

    public void addLineResult(String score, String line) {
        results.add("Result: " + score + ". Line: " + line);
    }

    public List<String> getResults() {
        return results;
    }

    public void sortResults() {
        System.out.println("sorting");
        Collections.sort(results, Collections.reverseOrder());
    }

    @Override
    public String toString() {
        return "id: " + getUniqueId() + " number of lines: " + getNumberOfLines() +
                ". isResultChecked: " + isResultChecked();
    }
}