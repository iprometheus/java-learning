package com.fangxing.javalearning.threadlearning;

import com.google.common.util.concurrent.*;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.FutureListener;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;
import java.util.function.Supplier;

@EnableAsync
public class AsyncLearingApplication {


    //异步
    public static void main(String[] args) throws Exception{


        //参考文档：
        // https://blog.csdn.net/xcwll_sina/article/details/50481062
        // https://blog.csdn.net/tangyongzhe/article/details/49851769

        //方法1:使用Future
        //testFuture();

        //方法2：使用Guava的Future
        //testGuavaFuture();

        //方法3：使用Guava的Future
        //testGuavaFuture2();

        //方法4：使用Guava的Future
        //testGuavaFuture3();

        //方法5：jdk1.8提供的Future
        //testCompletableFuture();

        //方法6：使用netty的Future
        //testNettyFuture();

        //方法7：使用@Async注解
        AsyncTask asyncTask=new AsyncTask();
        asyncTask.asyncNonReturnTask();
        System.out.println("the main thread is still running ");


    }


    public static void testFuture() throws Exception{
        /**
         *方法1：使用jdk1.8之前的FUture函数
         Future代表了线程执行完以后的结果，可以通过future获得执行的结果
         但是jdk1.8之前的Future有点鸡肋，并不能实现真正的异步，需要阻塞的获取结果，或者不断的轮询
         通常我们希望当线程执行完一些耗时的任务后，能够自动的通知我们结果，很遗憾这在原生jdk1.8之前
         是不支持的，但是我们可以通过第三方的库实现真正的异步回调
         *
         */

        int threadNum=1;
        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);

        Future<String> future=executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("start the async task");
                Thread.sleep(5000);
                System.out.println("end the async task");
                return "0000";
            }
        });


        String result= future.get();
        System.out.println("the result:"+result);
    }

    public static void testGuavaFuture(){
        ExecutorService executorService=Executors.newFixedThreadPool(1);

        //使用guava提供的MoreExecutors工具类包装原始的线程池
        ListeningExecutorService listeningExecutorService= MoreExecutors.listeningDecorator(executorService);

        //向线程池中提交一个任务后，将会返回一个可监听的Future，该Future由Guava框架提供
        ListenableFuture<String> lf=listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("star the async task");
                Thread.sleep(5000);
                System.out.println("end the async task");
                return "0000";
            }
        });


        //添加回调，回调由executor中的线程触发，但也可以指定一个新的线程
        Futures.addCallback(lf, new FutureCallback<String>() {

            //耗时任务执行失败后回调该方法
            @Override
            public void onSuccess(String s) {
                System.out.println("success");
            }

            //耗时任务执行成功后回调该方法
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure");
            }
        });

        //主线程可以继续做其他的工作
        System.out.println("main thread is running");
    }

    public static void testGuavaFuture2(){

        ExecutorService executorService=Executors.newFixedThreadPool(1);
        ListeningExecutorService listeningExecutorService= MoreExecutors.listeningDecorator(executorService);

        //向线程池中提交一个任务后，将会返回一个可监听的Future，该Future由Guava框架提供
        ListenableFuture<String> lf=listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("star the async task");
                Thread.sleep(5000);
                System.out.println("end the async task");
                return "0000";
            }
        });

        //添加回调，回调由executor中的线程触发，但也可以指定一个新的线程
        //注意这个实例与GuavaFuture1的区别：
        //如果没有指定回调函数使用的线程，默认和执行异步操作的线程是同一个
        //这里指定了回调函数使用的线程
        Futures.addCallback(lf, new FutureCallback<String>() {

            //耗时任务执行失败后回调该方法
            @Override
            public void onSuccess(String s) {
                System.out.println("success");
            }

            //耗时任务执行成功后回调该方法
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure");
            }
        },Executors.newSingleThreadExecutor());


        //主线程可以继续做其他的工作
        System.out.println("main thread is running");
    }

    public static void testGuavaFuture3(){

        ExecutorService executorService=Executors.newFixedThreadPool(1);
        ListeningExecutorService listeningExecutorService= MoreExecutors.listeningDecorator(executorService);

        //向线程池中提交一个任务后，将会返回一个可监听的Future，该Future由Guava框架提供
        ListenableFuture<String> lf=listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("star the async task");
                Thread.sleep(5000);
                System.out.println("end the async task");
                return "0000";
            }
        });

        //注意：针对本类，这里拿不到返回值
        //注册监听器,即异步调用完成时会在指定的线程池中执行注册的监听器
        lf.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("async finished");
            }
        },Executors.newSingleThreadExecutor());

        //主线程可以继续做其他的工作
        System.out.println("main thread is running");
    }

    public static void testCompletableFuture() throws Exception{
        //jdk1.8提供的异步实现方式
        ExecutorService executorService=Executors.newFixedThreadPool(2);

        CompletableFuture<String> future=CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("start the async task");
                try {
                    Thread.sleep(5000);
                }catch(Exception e){
                    System.out.println("exception accured");
                }
                System.out.println("end the async task");
                return "0000";
            }
        },executorService);

        future.thenAccept(result-> System.out.println(result+"ok"));
        System.out.println("main thread is still running");

    }

    public static void testNettyFuture(){
        //初始化线程池
        EventExecutorGroup group = new DefaultEventExecutorGroup(1);

        //向线程池中提交任务，并返回Future，该Future是netty自己实现的future
        //位于io.netty.util.concurrent包下，此处运行时的类型为PromiseTask
        io.netty.util.concurrent.Future<String> future=
        group.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("start the async task");
                Thread.sleep(5000);
                System.out.println("end the async task");
                return "0000";
            }
        });


        //增加监听
        future.addListener( new FutureListener() {
            @Override
            public void operationComplete(io.netty.util.concurrent.Future future) throws Exception {
                Object objResult= future.get();
                System.out.println("result:"+objResult.toString());
            }
        });

        System.out.println("main thread is still running");

    }

}
