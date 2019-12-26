import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static class Pool {
        private static final int MAX_AVAILABLE = 100;
        private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

        private Object[] items = new String[MAX_AVAILABLE];
        private boolean[] used = new boolean[MAX_AVAILABLE];

        public Object getItem() throws InterruptedException {
            available.acquire();
            return getNextAvailableItem();
        }

        public void putItem(Object object) {
            if (markAsUnused(object)) {
                available.release();
            }
        }

        public synchronized Object getNextAvailableItem() {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (!used[i]) {
                    used[i] = true;
                    return items[i];
                }
            }
            return null;
        }

        public synchronized boolean markAsUnused(Object object) {
            for (int i = 0; i < MAX_AVAILABLE; ++i) {
                if (object == items[i]) {
                    if (used[i]) {
                        used[i] = false;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
