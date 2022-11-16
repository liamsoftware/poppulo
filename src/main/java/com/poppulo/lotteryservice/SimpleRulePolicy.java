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

    private static final String TICKET_CONTAINS_SUM_OF_TWO = "10";
    private static final String TICKER_CONTAINS_SINGLE_MATCHING_VALUE = "5";
    private static final String TICKET_CONTAINS_SECOND_AND_THIRD_VALUES_DIFFERENT_FROM_FIRST = "1";
    private static final String NOT_A_WINNING_TICKET = "0";

    //get the big o notation for these - worse case analysis

    public String computeResult(Ticket ticket) {
        List<Integer[]> lines = ticket.getLines();
        log.info("computeResult: computing ticket id: {} with {} number of lines.", ticket.getUniqueId(),
                ticket.getNumberOfLines());

        if (isAnyLineASumOfTwo(lines)) return TICKET_CONTAINS_SUM_OF_TWO;
        if (doesAnyLineContainASingleValue(lines)) return TICKER_CONTAINS_SINGLE_MATCHING_VALUE;
        if (doesAnyLineContainDifferingThirdAndFourthValuesFromTheFirst(lines)) return TICKET_CONTAINS_SECOND_AND_THIRD_VALUES_DIFFERENT_FROM_FIRST;
        return NOT_A_WINNING_TICKET;
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