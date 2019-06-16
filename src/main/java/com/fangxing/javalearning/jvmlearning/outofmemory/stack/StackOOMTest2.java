package com.fangxing.javalearning.jvmlearning.outofmemory.stack;

/**
 * VM参数：Xss2m
 */
public class StackOOMTest2 {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOOMTest2 oom = new StackOOMTest2();
        oom.stackLeakByThread();
    }


}
