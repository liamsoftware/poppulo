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

    //if a line contains a sum of 2, then result for ticket is 10
    //else if a line contains all same nums, then result for ticket is 5
    //else if second and third nums are diff from first, then result for ticket is 1
    //otherwise result is 1.

    //get the big o notation for these - worse case analysis

    public String computeResult(Ticket ticket) {
        List<Integer[]> lines = ticket.getLines();
        log.info("computeResult: computing ticket id: {} with {} number of lines.", ticket.getUniqueId(),
                ticket.getNumberOfLines());
        if (isAnyLineASumOfTwo(lines)) return "10";
        else if (doesAnyLineContainASingleValue(lines)) return "5";
        else if (doesAnyLineContainDifferingThirdAndFourthValuesFromTheFirst(lines)) return "1";
        return "0";
    }

    private boolean isAnyLineASumOfTwo(List<Integer[]> lines) {
        for (Integer[] l : lines) {
            long sum = Arrays.stream(l).mapToLong(num -> num).sum();
            if (sum == 2) return true;
        }
        return false;
    }

    private boolean doesAnyLineContainASingleValue(List<Integer[]> lines) {
        for (Integer[] l : lines) {
            long count = Arrays.stream(l).distinct().count();
            if (count == 1) return true;
        }
        return false;
    }

    private boolean doesAnyLineContainDifferingThirdAndFourthValuesFromTheFirst(List<Integer[]> lines) {
        for (Integer[] l : lines) {
            if (l[1] != l[2]) {
                if (l[0] != l[1] && l[0] != l[2]) return true;
            }
        }
        return false;
    }
}