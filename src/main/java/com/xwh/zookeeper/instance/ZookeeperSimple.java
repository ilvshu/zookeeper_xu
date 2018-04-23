package com.xwh.zookeeper.instance;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/4/23.
 */
public class ZookeeperSimple implements Watcher {
    private static String connect = "master:2181";
    private static int timeout = 5000;
    private static ZooKeeper zk;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static ZooKeeper getZooKeeper() throws IOException {
        zk= new ZooKeeper(connect,timeout,new ZookeeperSimple());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("创建成功");
        return  zk;
    }

    public static void main(String[] args) throws IOException {
        ZooKeeper zookeeper = new ZooKeeper("master:2181",5000,new ZookeeperSimple());
        System.out.println(zookeeper.getState());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("code over");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                latch.countDown();
            } else if (Event.EventType.NodeChildrenChanged == watchedEvent.getType()) {
                try {
                    System.out.println("子节点为" + zk.getChildren(watchedEvent.getPath(), true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
