import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.RetryNTimes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class MyFirstJUnitJupiterTests {
    static CuratorFramework client1 = null;
    static CuratorFramework client2 = null;
    Logger logger = LoggerFactory.getLogger(MyFirstJUnitJupiterTests.class);

    static String obj = null;

    @BeforeAll
    public static void init() {
        client1 = CuratorFrameworkFactory.newClient("192.168.254.132:2181, 192.168.254.131:3181, 192.168.254.131:2181",
                new RetryNTimes(3, 500));

        client2 = CuratorFrameworkFactory.newClient("192.168.254.132:2181, 192.168.254.131:3181, 192.168.254.131:2181",
                new RetryNTimes(3, 500));
        client1.start();
        client2.start();
    }

    @Test
    @DisplayName("set barrier")
    public void testBarrier() {
        System.out.println("######## testBarrier");
//        Thread thread1 = new Thread(()->{
//            DistributedBarrier barrier = new DistributedBarrier(client1, "/barrier");
//            try {
//                barrier.waitOnBarrier();
//                logger.info("barrier passed");
//
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//            }
//        });
//
//        Thread thread2 = new Thread(()->{
//            DistributedBarrier barrier = new DistributedBarrier(client2, "/barrier");
//            try {
//                Thread.sleep(5000);
//                barrier.setBarrier();
//                logger.info("set barrier");
//            } catch (InterruptedException e) {
//                logger.error(e.getMessage());
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//            }
//        });
//
//        thread1.start();
//        thread2.start();
//
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//        }


        DistributedBarrier barrier = new DistributedBarrier(client2, "/barrier");
        try {
            barrier.setBarrier();
            System.out.println("####### set barrier");

            Thread.sleep(5000);
            barrier.removeBarrier();
            obj = "barrier";

            //logger.info("set barrier");
        } catch (InterruptedException e) {
            e.printStackTrace();
            //logger.error(e.getStackTrace().toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }


    @Test
    @DisplayName("barrier wait")
    public void testBarrierWait() {
        DistributedBarrier barrier = new DistributedBarrier(client1, "/barrier");
        try {
            barrier.waitOnBarrier();
            System.out.println("barrier passed");
            assertEquals("barrier", obj);
            //logger.info("barrier passed");

        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            assertEquals("barrier", obj);
        }
    }

    @AfterAll
    public static void close() {
        client1.close();
        client2.close();
    }
}
