import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ExecutorServiceExample {

    public static class MyExecutor {
        private ExecutorService executorService = Executors.newFixedThreadPool(5);

        public ExecutorService getExecutorService() {
            return executorService;
        }

        void run() {
            for (int i = 0; i < 11; i++){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("run task ---" + Thread.currentThread().getName() );
                    }
                });
            }

        }
    }

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.run();
    }
}
