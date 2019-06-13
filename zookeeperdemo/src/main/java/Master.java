import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Master implements Watcher {
    private final static Logger logger = LoggerFactory.getLogger(Master.class);
    ZooKeeper zooKeeper;
    String hostPort;

    Random random = new Random();
    String servverId = String.valueOf(random.nextLong());
    boolean isLeader = false;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void process(WatchedEvent event) {
        logger.debug(event.toString());
    }

    void start() throws Exception {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    boolean isLeader() {
        return isLeader;
    }

    boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] data = zooKeeper.getData("/master", false, stat);
                isLeader = new String(data).equals(servverId);
                return true;
            } catch (KeeperException ex) {
                if (ex.code() == KeeperException.Code.NONODE) {
                    logger.debug("NONODE");
                    return false;
                }
                logger.debug(ex.code().toString());
                return true;
            } catch (InterruptedException ex) {
            }

        }
    }

    public void runForMsater() throws InterruptedException {
        while (true) {
            try {
                zooKeeper.create("/master", servverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException ex) {
                //if (ex instanceof KeeperException.NodeExistsException){
                if (ex.code() == KeeperException.Code.NODEEXISTS) {
                    logger.info(ex.code().toString());
                    isLeader = false;
                    break;
                }
                logger.error(ex.getMessage());
            }
            if (checkMaster()) break;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                Master master = new Master("192.168.254.130:2181");
                try {
                    master.start();
                    master.runForMsater();
                    if (master.isLeader()) {
                        logger.info("I'am the leader");
                        Thread.sleep(10_000);
                    }
                } catch (Exception ex) {
                    logger.info(ex.getMessage());
                }
            }).start();
        }
    }
}
