import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class ReentrantLockTests {
    static CuratorFramework client1 = null;
    static CuratorFramework client2 = null;

    @BeforeAll
    public void init() {
        client1 = CuratorFrameworkFactory.newClient("192.168.254.132:2181, 192.168.254.131:3181, 192.168.254.131:2181",
                new RetryNTimes(3, 500));
        ConnectionStateListener connectionStateListener = new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {

            }
        };

        client1.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {

            }
        });


        client2 = CuratorFrameworkFactory.newClient("192.168.254.132:2181, 192.168.254.131:3181, 192.168.254.131:2181",
                new RetryNTimes(3, 500));
        client1.start();
        client2.start();
    }

    @Test
    public void process1() {
        InterProcessMutex lock = new InterProcessMutex(client1, "/lock-test");
        try {
            lock.acquire();
        } catch (Exception e) {

        }


    }
}
