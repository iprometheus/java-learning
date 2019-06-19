package com.fangxing.javalearning.concurrent.semaphore;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Bank implements Runnable {

    /**
     * 当前客户
     */
    private int consumer;

    public Bank(int consumer) {
        //客户拿号
        System.out.println("客户" + consumer + "请等待！");
        this.consumer = consumer;
    }

    /**
     * 当前正在服务的业务员的数量
     */
    private static int BANKER_COUNT = 2;
    private static Semaphore bankers = new Semaphore(BANKER_COUNT);

    @Override
    public void run() {

        try {
            //叫号并处理业务
            bankers.acquire();

            //开始处理业务
            System.out.println("客户" + consumer + "开始办理业务，还有" + bankers.availablePermits() + "业务员！");
            Thread.sleep(5000);
            System.out.println("客户" + consumer + "业务办理完毕！");

            //释放业务员
            bankers.release();

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
