package com.xwh.zookeeper.node;

import com.xwh.zookeeper.instance.ZookeeperSimple;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**同步创建节点
 * Created by Administrator on 2018/4/23.
 */
public class CreateNode{
    private CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zoo1 = ZookeeperSimple.getZooKeeper();
        //创建持久节点
        String str1 =  zoo1.create("/zk-test1","h1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("持久节点"+str1);
        //创建持久顺序节点
        String str2 = zoo1.create("/zk-test2","h2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("持久顺序节点"+str2);
        //创建临时节点
        String str3 =  zoo1.create("/zk-test3","h2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("临时节点"+str3);
        //创建临时顺序节点
        String str4= zoo1.create("/zk-test4","h4".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("临时顺序节点"+str4);
    }


}
