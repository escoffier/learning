import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {
    private CopyOnWriteArrayList<String> arrayList;

    public ArrayListDemo(CopyOnWriteArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayListDemo(int n) {
        this.arrayList = new CopyOnWriteArrayList<>();
        for (int i = 0 ; i <n ; i++) {
            arrayList.add(String.valueOf(i));
        }
    }

    public void testDemo() {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            //Runnable target = () -> {};
            final int j = i;
            threads[i] = new Thread(() -> {
                arrayList.add(String.valueOf(j));
            });

            threads[i].start();
        }

//        for (int i = 0; i < 4; i ++ ) {
//
//            arrayList.get()
//        }

        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void main(String[] args) {
        ArrayListDemo arrayListDemo = new ArrayListDemo(100);
    }
}
