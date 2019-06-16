package concurrent.semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaphoreTest {

    public static void main(String[] args) {

        int consumerCount = 5;
        int threadCount = 5;

        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        for (int consumerIndex = 1; consumerIndex < consumerCount; consumerIndex++) {
            threadPool.submit(new Bank(consumerIndex));
        }

        threadPool.shutdown();

    }


}
