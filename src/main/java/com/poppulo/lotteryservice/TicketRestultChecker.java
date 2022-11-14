package com.poppulo.lotteryservice;

import java.util.List;

public class TicketRestultChecker {

    public String getResult(Ticket ticket) {
        String result = ""; //should use string builder
        List<Integer[]> lines = ticket.getLines();
        for (Integer[] l : lines) {
            check10(l,result);
            if (result.isEmpty()) check5(l, result);
            if (result.isEmpty()) check1(l, result);
        }
        if (result.isEmpty()) {
            return "0";
        }
        //should really be returning an array list of integer results
        return result;
    }

    private void check10(Integer[] l, String result) {
        int sum = 0;
        for (Integer i : l) sum+=i;
        if (sum == 2) result += "10";
    }

    private void check5(Integer[] l, String result) {
        int sum = 0;
        for (Integer i : l) sum+=i;
        if (sum == 2) result += "5";
    }

    private void check1(Integer[] l, String result) {
        if (l[0] != l[1] && l[0] != l[2]) result += "1";
    }
}