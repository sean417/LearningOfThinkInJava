package jdk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock要与Condition配合使用，跟synchronized与wait(),notify()配合使用
 * 一样。
 * 首先通过Lock生成一个与之绑定的Condition对象。
 * 当condition.await();时，线程释放这把锁，并等待在这个condition上，只有
 * 别的线程condition.signal()系统会在当前condition对象的等待队列中随机唤醒一个线程，
 * 被唤醒的线程会尝试获得与condition绑定的锁，所以只有别的线程lock.unlock()后，被唤醒
 * 线程才有机会获得锁。
 * Created by chenyang on 2017/3/12.
 */
public class ReenterLockCondition implements Runnable{
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();

    public void run() {
        try {
            lock.lock();
            System.out.println("Thread is before await");

            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition rt1=new ReenterLockCondition();
        Thread t1=new Thread(rt1);
        t1.start();
        System.out.println("main thread sleep 2 second.");
        Thread.sleep(2000);

        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
