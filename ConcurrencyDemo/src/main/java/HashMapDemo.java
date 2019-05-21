import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class HashMapDemo {

    private Map<String, String> map;

    public HashMapDemo() {
        map = new HashMap<String, String>(100);
        map.put("key1", "value1");
    }

    private String randomString() {
        int leftLimit = 97;
        int rightlLimit = 122;
        int targetStringLength = 10;

        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder stringBuilder = new StringBuilder(targetStringLength);

        for (int i =0 ; i < targetStringLength; i++){
            int randomLimitInt = leftLimit + (int) (random.nextFloat() * (rightlLimit - leftLimit + 1));
            stringBuilder.append(randomLimitInt);
        }
       return stringBuilder.toString();
    }


    public void test() {
        Thread[] threads = new Thread[4];

        Map<String, String> synMap = Collections.synchronizedMap(map);
        //Map synMap = map;
        Thread  thread = new Thread(()-> {
            for (int i = 0 ; i < 100; i ++) {
                System.out.println(synMap.size());
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex ) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        thread.start();


        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 0; j < 1000; j++) {


                    String str = randomString();
                    System.out.println(Thread.currentThread().getName());
                    synMap.put(str, Thread.currentThread().getName());
                    //Thread.yield();
                }
            });

            threads[i].start();
        }


        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

        }
        try {
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        HashMapDemo hashMapDemo = new HashMapDemo();
        hashMapDemo.test();

    }
}
