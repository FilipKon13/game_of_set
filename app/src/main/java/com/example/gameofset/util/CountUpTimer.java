package com.example.gameofset.util;

import android.os.CountDownTimer;

public abstract class CountUpTimer extends CountDownTimer {

    public CountUpTimer(long countDownInterval) {
        super(Long.MAX_VALUE, countDownInterval);
    }

    public abstract void update(long millis);

    @Override
    public void onTick(long millisUntilFinished) {
        update(Long.MAX_VALUE - millisUntilFinished);
    }

    @Override
    public void onFinish() {}
}
