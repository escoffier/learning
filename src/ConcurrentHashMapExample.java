import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {

        ConcurrentHashMap<String ,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("A", 1);
        concurrentHashMap.put("B", 2);
        concurrentHashMap.put("C", 3);
        concurrentHashMap.put("D", 4);
        concurrentHashMap.put("E", 5);
        concurrentHashMap.put("F", 6);
        concurrentHashMap.put("G", 7);
        System.out.println("xxxx");

        concurrentHashMap.forEach(2, (k, v)-> {
            System.out.println("key->" + k + " is related with value->" + v + ", in thread" + Thread.currentThread().getName());
            ///System.out.println(Thread.currentThread().getName());
        });

    }
}
