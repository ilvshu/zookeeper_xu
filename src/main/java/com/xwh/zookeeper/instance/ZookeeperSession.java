package com.xwh.zookeeper.instance;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/4/23.
 */
public class ZookeeperSession implements Watcher{
    private static CountDownLatch latch = new CountDownLatch(1);
    private static String connectString = "master:2181";
    private static int sessionTimeout = 5000;

    public static void main(String[] args) throws IOException {
        ZooKeeper zoo1 = new ZooKeeper(connectString,sessionTimeout,new ZookeeperSession());
        System.out.println("zoo1 状态"+zoo1.getState());
        System.out.println("zoo1 sessionId"+zoo1.getSessionId());
        System.out.println("zoo1 sessionpwd"+zoo1.getSessionPasswd());
        ZooKeeper zoo2 = new ZooKeeper(connectString,sessionTimeout,new ZookeeperSession(),zoo1.getSessionId(),zoo1.getSessionPasswd());
        System.out.println("zoo2 状态"+zoo2.getState());
        System.out.println("zoo2 sessionId"+zoo2.getSessionId());
        System.out.println("zoo2 sessionpwd"+zoo2.getSessionPasswd());
        ZooKeeper zoo3 = new ZooKeeper(connectString,sessionTimeout,new ZookeeperSession(),1l,"test".getBytes());
        System.out.println("zoo3 状态"+zoo3.getState());
        System.out.println("zoo3 sessionId"+zoo3.getSessionId());
        System.out.println("zoo3 sessionpwd"+zoo3.getSessionPasswd());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("code over");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.toString());
    }
}
