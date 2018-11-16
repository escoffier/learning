public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();


        @Override
        public void run() {
            integerThreadLocal.set((int)(Math.random()* 10D));

            try {
                Thread.sleep(1000);


            } catch (InterruptedException e) {

            }

            System.out.println("thread: " + Thread.currentThread().getName() + integerThreadLocal.get().toString());
            System.out.println(integerThreadLocal.get());
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
        } catch (InterruptedException e)
        {

        }


    }
}
