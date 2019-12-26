import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryNTimes;

import java.io.EOFException;

public class LeaderLatchDemo {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("need parameter");
            return;
        }

        System.out.println("my id is : " + args[0]);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.254.131:2181")
                .sessionTimeoutMs(600 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(new RetryNTimes(3, 500))
                .build();

        client.start();
        LeaderLatch leaderLatch = new LeaderLatch(client, "/mylatch", args[0]);

        try {
            leaderLatch.start();
            //Thread.sleep(1000);
            if (!leaderLatch.hasLeadership()) {
                System.out.println("Waiting for leadership");
                try {
                    leaderLatch.await();

                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                } catch (EOFException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("####### I'am leader now!!!!");
            Thread.sleep(30 * 1000);
            leaderLatch.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

//        if (!leaderLatch.hasLeadership()) {
//            System.out.println("Waiting for leadership");
//            try {
//                leaderLatch.await();
//            } catch ( InterruptedException ex) {
//                System.out.println(ex.getMessage());
//            } catch (EOFException ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            System.out.println("I'am leader now!!!!");
//        }
    }
}
