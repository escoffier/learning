import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);
    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception {

        CuratorListener curatorListener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                try {
                    switch (event.getType()) {
                        case CREATE:
                            logger.info(event.getPath());
                            break;
                        //event.
                        case DELETE:
                            logger.info("delete path: " + event.getPath());
                            break;
                        case CHILDREN:
                            logger.info("children path: " + event.getPath());
                            logger.info("children : " + event.getChildren());
                            break;
                        case WATCHED:
                            logger.info("watched event: {} for path {}", event.getWatchedEvent().getType(), event.getPath());
                            //event.getWatchedEvent().getType()

                    }

                } catch (Exception e) {
                    logger.error(e.getMessage());
                    //close();
                }

            }
        };

        RetryPolicy retryPolicy = new RetryNTimes(3, 500);

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.254.132:2181, 192.168.254.131:3181, 192.168.254.131:2181", retryPolicy);


        client.getCuratorListenable().addListener(curatorListener);

        client.start();

        try {
            List<String> children = client.getChildren().watched().forPath("/works");
            logger.info(children.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        List<String> children1 = client.getChildren().inBackground().forPath("/works");

        client.getChildren().inBackground((cli, event) -> {
            logger.info("children from callback: " + event.getChildren().toString());
        }).forPath("/works");
//        (cli, event) -> {
//
//        }
        //logger.info("children: ", children1.toString());

        client.getData().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                try {
                    switch (event.getType()) {
                        case NodeDataChanged:
                            logger.info("node {} data changed", event.getPath());
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
            }
        }).inBackground((cli, event) -> {
            logger.info("data from callback: " + event.getData().toString());
        }).forPath("/works/work-2");

        synchronized (lock) {
            lock.wait();
        }
//        try {
//            client.wait();
//        } catch (InterruptedException e) {
//            logger.info(e.getMessage());
//        }


//        Thread thread = new Thread(() -> {
//           client.start();
//        });

    }
}
