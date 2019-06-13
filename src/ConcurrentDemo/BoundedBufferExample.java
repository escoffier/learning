package ConcurrentDemo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferExample {

    public static class BoundedBuffer{
        final private ReentrantLock lock = new ReentrantLock();
        final private Condition notFull  = lock.newCondition();
        final private Condition notEmpty = lock.newCondition();

        final private Object[] items = new Object[100];
        private int count, putptr, takeptr;

        public void put(Object object) throws InterruptedException {
            lock.lock();
            try{
                //buffer is full, block current thread
                while (count == items.length) {
                    notFull.await();
                }
                items[putptr] = object;
                if (++putptr == items.length) putptr = 0;
                ++count;
                notEmpty.signal();

            } finally {
                lock.unlock();

            }
        }

        public Object take() throws InterruptedException {
            lock.lock();

            try {
                while (count == 0) {
                    notEmpty.await();
                }
                Object obj = items[takeptr];
                if (++takeptr == items.length) takeptr = 0;
                --count;
                notFull.signal();
                return obj;
            } finally {
                lock.unlock();


            }
        }
    }

    private static class Producer implements Runnable {
        final private BoundedBuffer boundedBuffer;

        Producer(BoundedBuffer buffer) {
            boundedBuffer = buffer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 90; i++){
                try {
                    boundedBuffer.put(new Integer(i));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }

            }

        }
    }

    private static class Consumer implements Runnable {
        final private BoundedBuffer boundedBuffer;

        Consumer(BoundedBuffer buffer) {
            boundedBuffer = buffer;
        }

        @Override
        public void run() {
            while (true){

                try{
                    Integer item = (Integer) boundedBuffer.take();
                    System.out.println(Thread.currentThread().getName() + " Item : " +  item);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }

            }

        }
    }

    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        Producer producer = new Producer(buffer);

        Consumer consumer = new Consumer(buffer);

        Thread producerThread = new Thread(producer);

        Thread[] consumerThread = new Thread[2];

        for (int i = 0; i < 2; i++){
            consumerThread[i] = new Thread(consumer);
            consumerThread[i].start();
        }

        producerThread.start();

        try {
            producerThread.join();
            for (Thread th: consumerThread
            ) {
                th.join();
            }
        } catch (InterruptedException e) {}

        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();


    }
}
