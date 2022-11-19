package com.poppulo.lotteryservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Ticket {

    private long uniqueId;
    private List<Integer[]> lines = new ArrayList<>();
    private boolean resultChecked = false;
    private List<LineResult> lineResults =  new ArrayList<>();

    public Ticket(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void addLine(Integer[] line) {
        lines.add(line);
    }

    public int getNumberOfLines() {
        return lines.size();
    }

    public void sortResults() {
        Collections.sort(lineResults);
    }

    public void addResult(LineResult lineResult) {
        lineResults.add(lineResult);
    }

    @Override
    public String toString() {
        return "id: " + getUniqueId() + " number of lines: " + getNumberOfLines() +
                ". isResultChecked: " + isResultChecked();
    }
}