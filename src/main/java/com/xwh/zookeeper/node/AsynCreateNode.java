package com.xwh.zookeeper.node;

import com.xwh.zookeeper.instance.ZookeeperSimple;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Administrator on 2018/4/23.
 */
public class AsynCreateNode {

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zoo = ZookeeperSimple.getZooKeeper();
        zoo.create("/zk-asyn-tset","asyn".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallback(),"Test");
        Thread.sleep(Integer.MAX_VALUE);
    }

}
class IStringCallback implements AsyncCallback.StringCallback{
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("创建结果"+i+" "+s +" " + o + " "+s1);
    }
}
