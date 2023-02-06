package com.example.gameofset.util;

import android.os.CountDownTimer;

public abstract class AdditiveCountDownTimer {
    private CountDownTimer timer;
    private long curr_time;
    private final long interval;
    public abstract void onTick(long millisUntilFinished);
    public abstract void onFinish();

    private class CountDownTimerImpl extends CountDownTimer {
        public CountDownTimerImpl(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            curr_time = millisUntilFinished;
            AdditiveCountDownTimer.this.onTick(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            AdditiveCountDownTimer.this.onFinish();
        }
    }

    public AdditiveCountDownTimer(long millis, long interval) {
        curr_time = millis;
        this.interval = interval;
        timer = new CountDownTimerImpl(curr_time, interval);
    }

    public void start() {
        timer.start();
    }

    public void addTime(long millis) {
        timer.cancel();
        curr_time += millis;
        timer = new CountDownTimerImpl(curr_time, interval);
        timer.start();
    }
}
