package com.fangxing.javalearning.eventbus;

import com.google.common.eventbus.EventBus;

public class BusMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("Test");
        eventBus.register(new MyObserve());
        eventBus.post("suxiaolong");
    }

}
