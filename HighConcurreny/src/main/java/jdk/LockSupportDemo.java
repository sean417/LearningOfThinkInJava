package jdk;

import ParallelBasic.BadSuspend;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport用的是锁的底层源语操作，很多jdk类用的都是它。
 * park()也是阻塞线程类似suspend()和wait(),
 * 区别是，不想suspend()和resume(),如果resume()在suspend()前面，则线程永远阻塞。
 * 原因是park()和unpark()采用了类似信号量的技术。
 * 如何线程线程没有许可时，unpark()使线程有一个许可，当park()时，不会阻塞，同时park()
 * 消费一个许可。当线程在park()阻塞时，unpark()会使线程直接跳出阻塞，往下执行，同时消费一个许可
 * 注意：一个线程最多只有一个许可.
 * park()不会抛出中断异常，但是能够响应中断。线程一被中断，立即返回，
 * 可以通过Thread.interrupted()获得中断标志
 * Created by chenyang on 2017/3/13.
 */
public class LockSupportDemo {
    public static Object u=new Object();
    static ChangeObjectThread t1=new ChangeObjectThread("t1");
    static ChangeObjectThread t2=new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }
}
