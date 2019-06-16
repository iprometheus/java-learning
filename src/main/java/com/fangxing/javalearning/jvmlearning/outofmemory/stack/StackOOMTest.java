package com.fangxing.javalearning.jvmlearning.outofmemory.stack;

/**
 * VM参数： -Xss128k
 */
public class StackOOMTest {


    private int stackLength = 1;

    public void stackLeagth() {
        stackLength++;
        stackLeagth();
    }

    public static void main(String[] args) {
        StackOOMTest oom = new StackOOMTest();
        try {
            oom.stackLeagth();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

}
