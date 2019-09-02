package com.fangxing.javalearning.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String a = "testa";
                    String str = exchanger.exchange(a);
                    System.out.println("aaaaa:" + str);
                } catch (Exception e) {

                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String a = "testb";
                    String str = exchanger.exchange(a);
                    System.out.println("bbbbb:" + str);
                } catch (Exception e) {

                }
            }
        });

        threadPool.shutdown();

    }

}
