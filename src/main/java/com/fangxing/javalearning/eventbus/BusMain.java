package com.fangxing.javalearning.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.math.BigDecimal;

public class BusMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("Test");
        eventBus.register(new MyObserve());
        eventBus.register(new BigDecimalObserve());
        eventBus.register(new IntObserve());
        eventBus.register(new Object() {
            @Subscribe
            public void listen(DeadEvent event) {
                System.out.println("deadEvent" + event.getEvent().toString());
            }
        });

        eventBus.post("suxiaolong");
        eventBus.post(new BigDecimal(333));
        int intMsg=666;
        eventBus.post(intMsg);
    }

}
