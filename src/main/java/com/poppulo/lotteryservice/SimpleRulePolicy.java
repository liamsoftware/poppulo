package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Check the line of a ticket and returns the score.
 * Score 10 if sum of values equals 2
 * Score 5 if all values are the same.
 * Score 1 if second and third values are different from the first.
 * Score 0 otherwise.
 */
public class SimpleRulePolicy implements RulePolicy {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String getResult(Ticket ticket) {
        String result = ""; //should use string builder
        List<Integer[]> lines = ticket.getLines();
        for (Integer[] l : lines) {
            log.info("getResult: check line: {}", Arrays.toString(l));
            result += check10(l,result);
            if (result.isEmpty()) result += check5(l, result);
            if (result.isEmpty()) result += check1(l, result);
        }
        if (result.isEmpty()) {
            return "0";
        }
        //should really be returning an array list of integer results
        log.info("getResult: result for ticket: [{}] is {}", ticket, result);
        return result;
    }

    private String check10(Integer[] l, String result) {
        long sum = Arrays.stream(l).mapToLong(num -> num).sum();
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