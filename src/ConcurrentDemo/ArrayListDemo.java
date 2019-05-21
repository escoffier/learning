package ConcurrentDemo;

import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {
    private CopyOnWriteArrayList<String> arrayList;

    public ArrayListDemo(CopyOnWriteArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayListDemo() {
        this.arrayList = new CopyOnWriteArrayList<>();
    }
}
