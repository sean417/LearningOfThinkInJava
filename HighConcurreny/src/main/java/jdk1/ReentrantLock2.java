package jdk1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock:
 * 一个线程可以再次获得锁资源。同时必须释放两次锁，其他线程才能获得锁资源

 * Created by chenyang on 2017/3/10.
 */
public class ReentrantLock2 implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public static int i=0;

    public void run() {
        for(int j=0;j<10000000;j++){
            lock.lock();
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
//                lock.unlock();//如何只释放一次unlock()那么别的线程第一个lock都进不去，用jstack可以看到
                                //进程二在17行等待资源，Object.wait():
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReentrantLock2 r=new ReentrantLock2();
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
