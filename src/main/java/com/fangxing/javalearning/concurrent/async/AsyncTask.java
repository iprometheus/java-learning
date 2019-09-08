package com.fangxing.javalearning.concurrent.async;


import org.springframework.scheduling.annotation.Async;

public class AsyncTask {

    @Async
    public void asyncNonReturnTask() throws Exception {

        System.out.println("start the async task");
        Thread.sleep(5000);
        System.out.println("end the async task");

    }

    @Async
    public void asyncNonReturnTask2() throws Exception {

        System.out.println("start the async task2");
        Thread.sleep(5000);
        System.out.println("end the async task2");

    }

}
