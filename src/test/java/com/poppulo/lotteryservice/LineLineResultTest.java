package com.poppulo.lotteryservice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LineLineResultTest {

    @Test
    public void compareHighTest() {
        LineResult high = new LineResult(10, "[0,1,2]");
        LineResult low = new LineResult(0, "[0,1,0]");
        assertEquals(-1, high.compareTo(low));
    }

    @Test
    public void compareLowTest() {
        LineResult high = new LineResult(10, "[0,1,2]");
        LineResult low = new LineResult(0, "[0,1,0]");
        assertEquals(1, low.compareTo(high));
    }

    @Test
    public void compareEqualsTest() {
        LineResult firstEqual = new LineResult(10, "[0,1,2]");
        LineResult secondEqual = new LineResult(10, "[0,1,0]");
        assertEquals(0, firstEqual.compareTo(secondEqual));
    }

    @Test
    public void toStringTest() {
        LineResult high = new LineResult(10, "[0,1,2]");
        assertTrue(high.toString().contains("Result: 10"));
    }
}
