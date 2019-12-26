package ConcurrentDemo;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    public void doSomeThing() {
        lock.lock();
        try {
            System.out.println("lock demo");
            Thread.sleep(10_000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {


        LockDemo lockDemo = new LockDemo();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(() -> lockDemo.doSomeThing());
            threads[i].start();
        }

        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getCause());
            }
        }
    }
}
