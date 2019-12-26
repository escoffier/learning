import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;

public class LeaderElectionDemo {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.254.131:2181")
                .sessionTimeoutMs(600 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(new RetryNTimes(3, 500))
                .build();

        client.start();

        LeaderSelectorListener listener = new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("###### i am leader now !");
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("##### new state " + newState);

            }
        };

        LeaderSelectorListenerAdapter listenerAdapter = new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("###### i am leader now !");
            }
        };

        LeaderSelector leaderSelector = new LeaderSelector(client, "/myleaderselector", listenerAdapter);


        leaderSelector.start();

        Thread.sleep(30 * 1000);
        leaderSelector.close();
    }
}
