package com.example.gameofset.util;

public class TimeDispenser {
    private final long base;
    private final double alpha;
    private long counter = 0;
    public TimeDispenser(long base, double alpha) {
        this.base = base;
        this.alpha = alpha;
    }

    public long getNext() {
        counter++;
        return Math.round(base * Math.pow(counter, alpha));
    }
}
