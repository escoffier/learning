import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.RetryNTimes;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.254.131:2181")
                .sessionTimeoutMs(600 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(new RetryNTimes(3, 500))
                .build();

        client.start();
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, "/lock_path");

        readWriteLock.readLock();
    }
}
