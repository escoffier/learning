package ConcurrentDemo;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    public void run() {
        lock.lock();
        try {
            System.out.println("lock demo");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        Thread thread = new Thread(() -> lockDemo.run());
        thread.start();
    }
}
