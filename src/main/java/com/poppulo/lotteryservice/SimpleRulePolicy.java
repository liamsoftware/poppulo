package com.poppulo.lotteryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Check the line of a ticket and returns the score.
 * Score 10 if sum of values equals 2
 * Score 5 if all values are the same.
 * Score 1 if second and third values are different from the first.
 * Score 0 otherwise.
 */
public class SimpleRulePolicy implements RulePolicy {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static final int TICKET_CONTAINS_SUM_OF_TWO = 10;
    public static final int TICKET_CONTAINS_SINGLE_MATCHING_VALUE = 5;
    public static final int TICKET_CONTAINS_SECOND_AND_THIRD_VALUES_DIFFERENT_FROM_FIRST = 1;
    public static final int NOT_A_WINNING_TICKET = 0;

    //get the big o notation for these - worse case analysis
    public Ticket computeResult(Ticket ticket) {
        if (ticket.isResultChecked()) {
            return ticket;
        }

        List<Integer[]> lines = ticket.getLines();
        log.info("computeResult: computing ticket id: {} with {} number of lines.", ticket.getUniqueId(),
                ticket.getNumberOfLines());

        lines.forEach(aLine -> {
            int score = checkResult(aLine);
            Result r = new Result(score, Arrays.toString(aLine));
            ticket.addResult(r);
        });

        ticket.sortResults();
        ticket.setChecked();
        return ticket;
    }

    private int checkResult(Integer[] aLine) {
        if (isSumOfTwo(aLine)) {
            return TICKET_CONTAINS_SUM_OF_TWO;
        }

        if (doesContainSingleValue(aLine)) {
            return TICKET_CONTAINS_SINGLE_MATCHING_VALUE;
        }

        if (doesContainDifferingThirdAndFourthValuesFromTheFirst(aLine)) {
            return (TICKET_CONTAINS_SECOND_AND_THIRD_VALUES_DIFFERENT_FROM_FIRST);
        }

        return NOT_A_WINNING_TICKET;
    }

    private boolean isSumOfTwo(Integer[] aLine) {
        long sum = Arrays.stream(aLine).mapToLong(num -> num).sum();
        return sum == 2;
    }

    private boolean doesContainSingleValue(Integer[] aLine) {
        long count = Arrays.stream(aLine).distinct().count();
        return count == 1;
    }

    private boolean doesContainDifferingThirdAndFourthValuesFromTheFirst(Integer[] aLine) {
        if (!Objects.equals(aLine[1], aLine[2])) {
            return !Objects.equals(aLine[0], aLine[1]) && !Objects.equals(aLine[0], aLine[2]);
        }
        return false;
    }
}