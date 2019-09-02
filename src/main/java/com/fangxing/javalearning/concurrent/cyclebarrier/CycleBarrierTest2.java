package com.fangxing.javalearning.concurrent.cyclebarrier;

import java.util.concurrent.CyclicBarrier;

public class CycleBarrierTest2 {

    private static CyclicBarrier barrier = new CyclicBarrier(2, new firstRun());

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (Exception e) {
                }
                System.out.println("1");
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    barrier.await();
                }catch (Exception e){

                }
                System.out.println("2");
            }
        }).start();

        System.out.println("3");

    }


    static class firstRun implements Runnable {

        @Override
        public void run() {
            System.out.println("first");
        }

    }

}
