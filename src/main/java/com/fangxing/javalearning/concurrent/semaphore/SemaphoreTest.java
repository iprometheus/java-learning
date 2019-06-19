package com.fangxing.javalearning.concurrent.semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaphoreTest {

    public static void main(String[] args) {

        String abc="abc";
        String bcd="bcd";
        String cde=abc+bcd;

        int consumerCount = 5;
        int threadCount = 5;

        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        for (int consumerIndex = 1; consumerIndex < consumerCount; consumerIndex++) {
            threadPool.submit(new Bank(consumerIndex));
        }

        threadPool.shutdown();

    }


}
