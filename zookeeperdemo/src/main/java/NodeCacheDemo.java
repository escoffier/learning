import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeCacheDemo {
    private static final Logger logger = LoggerFactory.getLogger(NodeCacheDemo.class);

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                //.connectString("192.168.254.132:2181, 192.168.254.131:3181, 192.168.254.131:2181")
                //.connectString("192.168.254.131:22181")
                .connectString("127.0.0.1:2181, 127.0.0.1:2182, 127.0.0.1:2183")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(new RetryNTimes(3, 500))
                .build();
        client.start();

        NodeCache nodeCache = new NodeCache(client, "/works/work-2");
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                logger.info("node data changed!");
                logger.info("new data: {}", nodeCache.getCurrentData().getData());
            }
        });
        nodeCache.start(true);

        ChildData data = nodeCache.getCurrentData();
        logger.info(data.toString());

        Thread.sleep(30_000);

        nodeCache.close();

        client.close();
    }
}
