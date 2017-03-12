package jdk;

import java.util.concurrent.locks.ReentrantLock;

/**公平锁：
 * 使每个线程公平的获得锁，原理：根据申请锁资源的线程先来后到形成队列，按照顺序获得锁。
 * 非公平锁不是按照顺序获得锁。
 * 劣势：公平锁比较耗费资源。
 * Created by chenyang on 2017/3/12.
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock=new ReentrantLock(true);

    public void run() {
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName()+" 获得锁");
            }finally {
                fairLock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        FairLock r1=new FairLock();
        Thread t1=new Thread(r1,"Thread_t1");
        Thread t2=new Thread(r1,"Thread_t2");
        t1.start();t2.start();
    }
}
