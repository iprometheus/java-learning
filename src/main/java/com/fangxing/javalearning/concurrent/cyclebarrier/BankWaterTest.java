package com.fangxing.javalearning.concurrent.cyclebarrier;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BankWaterTest implements Runnable {

    private CyclicBarrier barrier = new CyclicBarrier(4, this);

    private Executor executor= Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetCount=new ConcurrentHashMap<>();

    private void count(){
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetCount.put(Thread.currentThread().getName(),1);
                    try{
                        barrier.await();
                    }catch (Exception e){

                    }
                }
            });
        }
    }

    @Override
    public void run() {
        System.out.println("result"+sheetCount.size());
    }

    public static void main(String[] args) {

        BankWaterTest test3=new BankWaterTest();
        test3.count();

    }



}
