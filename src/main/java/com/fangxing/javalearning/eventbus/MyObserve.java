package com.fangxing.javalearning.eventbus;

import com.google.common.eventbus.Subscribe;

public class MyObserve {

    @Subscribe
    public void onEvent(String msg) {
        System.out.println("hello" + msg);
    }

}
