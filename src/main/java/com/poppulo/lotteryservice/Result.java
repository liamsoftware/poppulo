package com.poppulo.lotteryservice;

public class Result implements Comparable<Result> {

    private final int score;
    private final String line;

    public Result(int score, String line) {
        this.score = score;
        this.line = line;
    }

    public int getScore() {
        return score;
    }

    public String getLine() {
        return line;
    }

    @Override
    public int compareTo(Result otherResult) {
        if (getScore() < otherResult.getScore()) return 1;
        return -1;
    }

    @Override
    public String toString() {
        return "Result: " + getScore() + "\tLine: " + getLine();
    }
}
