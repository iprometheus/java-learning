package com.fangxing.javalearning.jvmlearning.finalizemethod;

/**
 * 此代码演示了两点：
 * 1.对象可以在被GC时自救
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次。
 */
public class FinalizeMethodTest {

    public static FinalizeMethodTest SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes ,i still alive :>");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeMethodTest();

        //第1次：对象拯救自己成功
        SAVE_HOOK = null;
        System.gc();
        //Finalze方法优先级很低，这里暂停0.5秒等待finalize方法被执行
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no ,i am dead :<");
        }

        //第2次：对象拯救自己失败(此段代码与上面完全一样)
        SAVE_HOOK = null;
        System.gc();
        //Finalze方法优先级很低，这里暂停0.5秒等待finalize方法被执行
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no ,i am dead :<");
        }
    }
}
