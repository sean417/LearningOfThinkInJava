package jdk1;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**读写锁：ReentrantReadWriteLock，读读不互斥，读写互斥，写写互斥。
 * 这样读的线程都是并行的，适合读的线程多于写的线程，这是并发高。
 * Created by chenyang on 2017/3/12.
 */
public class ReadWriteLockDemo {
    private static Lock lock=new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static Lock readLock=readWriteLock.readLock();
    private static Lock writeLock=readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);//读耗时越多读写锁的优势越明显
            System.out.println("read finish");
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock,int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value=index;
            System.out.println("write finish");

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {


        final ReadWriteLockDemo demo=new ReadWriteLockDemo();
        Runnable readRunnable=new Runnable() {
            public void run() {
                try {
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        final Runnable writeRunnable=new Runnable() {
            public void run() {
                try {
                    demo.handleWrite(writeLock,new Random().nextInt());
//                    demo.handleWrite(lock,new Random().nextInt());

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        for(int i=0;i<18;i++){
            new Thread(readRunnable).start();
        }
        for(int i=18;i<20;i++){
            new Thread(writeRunnable).start();
        }
    }
}
