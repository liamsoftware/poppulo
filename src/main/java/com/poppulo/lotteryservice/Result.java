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

    @Override
    public int compareTo(Result otherResult) {
        if (getScore() < otherResult.getScore()) return 1;
        else if (getScore() > otherResult.getScore()) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Result: " + score + "\tLine: " + line;
    }
}
