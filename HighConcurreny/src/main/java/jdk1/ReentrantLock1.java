package jdk1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock是synchronized的增强版。
 * 性能上从1.5后，synchronized与ReentrantLock差不多。
 * 但是功能多了:
 * 1.可重入。
 * 2.中断响应。
 * 3.锁申请等待限时。
 * 4.公平锁。
 * Created by chenyang on 2017/3/10.
 */
public class ReentrantLock1 implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public static int i=0;

    public void run() {
        for(int j=0;j<10000000;j++){
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();//程序退出前执行释放锁，对于synchronized系统会自动释放锁，不用客户端代码主动去释放。
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReentrantLock1 r=new ReentrantLock1();
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
