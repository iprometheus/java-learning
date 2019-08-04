package com.fangxing.javalearning.eventbus;

import com.google.common.eventbus.Subscribe;

public class IntObserve {

    @Subscribe
    public void onEvent(int msg) {
        System.out.println("hello" + msg);
    }

}
