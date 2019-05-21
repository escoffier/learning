public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized code block");
        }
    }

    public synchronized void method1() {
        System.out.println("synchronized code block");
    }
}
