package com.poppulo.lotteryservice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResultTest {

    @Test
    public void compareHighTest() {
        Result high = new Result(10, "[0,1,2]");
        Result low = new Result(0, "[0,1,0]");
        assertEquals(-1, high.compareTo(low));
    }

    @Test
    public void compareLowTest() {
        Result high = new Result(10, "[0,1,2]");
        Result low = new Result(0, "[0,1,0]");
        assertEquals(1, low.compareTo(high));
    }

    @Test
    public void compareEqualsTest() {
        Result firstEqual = new Result(10, "[0,1,2]");
        Result secondEqual = new Result(10, "[0,1,0]");
        assertEquals(0, firstEqual.compareTo(secondEqual));
    }

    @Test
    public void toStringTest() {
        Result high = new Result(10, "[0,1,2]");
        assertTrue(high.toString().contains("Result: 10"));
    }
}
