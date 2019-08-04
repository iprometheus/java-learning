package com.fangxing.javalearning.eventbus;

import com.google.common.eventbus.Subscribe;

import java.math.BigDecimal;

public class BigDecimalObserve {

    @Subscribe
    public void onEvent(BigDecimal msg) {
        System.out.println("hello" + msg);
    }

}
