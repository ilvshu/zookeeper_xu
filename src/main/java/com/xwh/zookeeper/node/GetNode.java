package com.xwh.zookeeper.node;

import com.xwh.zookeeper.instance.ZookeeperSimple;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */
public class GetNode {
    private static ZooKeeper zoo;
    private static String basePath = "/zk-test1";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        zoo = ZookeeperSimple.getZooKeeper();
        List<String> list =  zoo.getChildren("/zk-test1",true);
        System.out.println(list);
        zoo.create(basePath+"/e","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        zoo.create(basePath+"/d","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
