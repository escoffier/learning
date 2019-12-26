public class VolatileTest {

    public static volatile int race = 0;

    private static final int THREAD_COUNT = 20;

    volatile public int l1;

    volatile static long count = 17;

    static long printVal = 0;

    public void testRun() {
        int i = l1;
        l1 = i + 1;

//        if (l == -1) {
//            l = -1;
//            System.out.println(l);
//
//            return;
//        }
    }

    public static volatile long l;

    public static void run() {
        if (l == -1)
            //return;
            System.exit(-1);

        if (l == -2)
            //return;
            System.exit(-1);
    }

    public static void increase() {
        race++;
    }

    public static void volatileRead1() {
        long c = Long.MAX_VALUE >>> 27;
        for (int i = 0; i < 2; i++) {
            long s = System.currentTimeMillis();
            c += c;
            while (printVal < c) {
                printVal += 17;
                run();
            }
            long e = System.currentTimeMillis();
            System.out.printf("start = %s, e - s = %s \n", s, e - s);
        }
    }

    public static void volatileRead() {
        long c = Long.MAX_VALUE >>> 27;
        for (int i = 0; i < 2; i++) {
            long s = System.currentTimeMillis();
            c += c;
            while (printVal < c) {
                printVal += count;
            }
            long e = System.currentTimeMillis();
            System.out.printf("start = %s, e - s = %s \n", s, e - s);
        }
    }

    public static void main(String[] args) {
//        VolatileTest volatileTest = new VolatileTest();
//        volatileTest.testRun();
//
//        for (int i = 0; i < 10000; i++) {
//            increase();
//        }

        //increase();

//        Thread[] threads = new Thread[THREAD_COUNT];
//
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 1000; i++) {
//                        increase();
//                    }
//                }
//            });
//
//            threads[i].start();
//        }
//
//        System.out.println(race);
//        System.out.println(Thread.activeCount());
        //volatileRead1();

//        run();

        volatileRead();
    }
}
