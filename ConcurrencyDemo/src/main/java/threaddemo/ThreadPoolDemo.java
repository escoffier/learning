package threaddemo;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {

    static class CustomThreadPool extends ThreadPoolExecutor {

        private static final int QUEUE_CAP = 100;

        //private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(QUEUE_CAP);

        CustomThreadPool(int corePoolSize,
                         int maximumPoolSize,
                         long keepAliveTime,
                         TimeUnit unit,
                         BlockingQueue<Runnable> workQueue,
                         ThreadFactory threadFactory) {

            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }
    }

    static class SimpleThreadFactory implements ThreadFactory {

        private static AtomicInteger index = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "thread-" + index.getAndIncrement());
        }
    }


    public static void fixedPoolTest() {
        //unbounded queue
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 200 ; i++) {
            executorService.execute(() -> {System.out.println(Thread.currentThread().getName());});
        }
    }

    public static void fixedPoolTestBoundedQueue() {
        CustomThreadPool customThreadPool = new CustomThreadPool(2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new SimpleThreadFactory());

        for (int i = 0; i < 400; i++) {
            customThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

    public static void fixedPoolTestUnBoundedQueue() {
        CustomThreadPool customThreadPool = new CustomThreadPool(4,
                4,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new SimpleThreadFactory());

        for (int i = 0; i < 200; i++) {
            customThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

    public static void main(String[] args) {
        //fixedPoolTestUnBoundedQueue();

        //fixedPoolTest();

        fixedPoolTestBoundedQueue();



    }
}
