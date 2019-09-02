package com.fangxing.javalearning.concurrent.cyclebarrier;

import java.util.concurrent.CyclicBarrier;

public class CycleBarrierTest {

    //private static CyclicBarrier c = new CyclicBarrier(2);
    private static CyclicBarrier c = new CyclicBarrier(4);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.wait();
                    System.out.println("abc");//CyclicBarrier参数为3的时候：abc不会打印
                }catch (Exception e){

                }
            }
        }).start();

        try {
            c.wait();
            System.out.println("3");//CyclicBarrier参数为3的时候：3不会打印
        }catch (Exception e){
            int i=0;
        }


        System.out.println("2");
    }

}
