package com.poppulo.lotteryservice;

public class LineResult implements Comparable<LineResult> {

    private final int result;
    private final String line;

    public LineResult(int result, String line) {
        this.result = result;
        this.line = line;
    }

    public int getResult() {
        return result;
    }

    public String getLine() {
        return line;
    }

    @Override
    public int compareTo(LineResult otherLineResult) {
        if (getResult() < otherLineResult.getResult()) return 1;
        else if (getResult() > otherLineResult.getResult()) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Result: " + getResult() + ".\tLine: " + getLine();
    }
}
