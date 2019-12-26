import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalExample {

    public static class ThreadId {
        // Atomic integer containing the next thread ID to be assigned
        private static final AtomicInteger nextId = new AtomicInteger(0);

        // Thread local variable containing each thread's ID
        private static final ThreadLocal<Integer> threadId =
                ThreadLocal.withInitial(() -> nextId.getAndIncrement());

        // Returns the current thread's unique ID, assigning it if necessary
        public static int get() {
            return threadId.get();
        }
    }

    public static class MyRunnable implements Runnable {

        //private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            //integerThreadLocal.set((int)(Math.random()* 10D));

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {

            }
            System.out.println(ThreadId.get());

            //System.out.println("thread: " + Thread.currentThread().getName() + integerThreadLocal.get().toString());
            //System.out.println(integerThreadLocal.get());
        }
    }

    public static void main(String[]  args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable);

        Thread thread2 = new Thread(myRunnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {

        }


    }
}
