package com.poppulo.lotteryservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketRestultChecker {

    public String getResult(Ticket ticket) {
        String result = ""; //should use string builder
        List<Integer[]> lines = ticket.getLines();
        for (Integer[] l : lines) {
            result += check10(l,result);
            if (result.isEmpty()) result += check5(l, result);
            if (result.isEmpty()) result += check1(l, result);
        }
        if (result.isEmpty()) {
            return "0";
        }
        //should really be returning an array list of integer results
        return result;
    }

    private String check10(Integer[] l, String result) {
        int sum = 0;
        for (Integer i : l) sum+=i;
        if (sum == 2) result += "10";
        return result;
    }

    private String check5(Integer[] l, String result) {
        long count = Arrays.stream(l).distinct().count();
        if (count == 1) result += "5";
        return result;
    }

    private String check1(Integer[] l, String result) {
        //second and third values are different from the first
        if (l[1] != l[2]) {
            if (l[0] != l[1] && l[0] != l[2]) result += "1";
        }
        return result;
    }
}