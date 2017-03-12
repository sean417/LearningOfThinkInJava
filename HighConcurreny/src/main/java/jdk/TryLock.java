package jdk;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock使用没有参数的trylock()，
 * 如果得不到锁trylock()会立即返回false,否则为ture;
 * 本代码采用线程不断地尝试获取锁的资源，来得到两个锁
 * Created by chenyang on 2017/3/12.
 */
public class TryLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try{
                        try {
                        Thread.sleep(500);
                        }catch (InterruptedException e){
                        }
                        if (lock2.tryLock()) {
                            try{
                                System.out.println(Thread.currentThread().getId()+":My Job done");
                                return;
                            }finally {
                                lock2.unlock();//解锁，让别的线程获得锁资源
                            }
                        }
                    }finally {
                        lock1.unlock();//解锁，让别的线程获得锁资源
                    }
                }
            }
        }else {
            while (true) {
                if (lock2.tryLock()) {
                    try{
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                        }
                        if (lock1.tryLock()) {
                            try{
                                System.out.println(Thread.currentThread().getId()+":My Job done");
                                return;
                            }finally {
                                lock1.unlock();//解锁，让别的线程获得锁资源
                            }
                        }
                    }finally {
                        lock2.unlock();//解锁，让别的线程获得锁资源
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock r1=new TryLock(1);
        TryLock r2=new TryLock(2);
        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);
        t1.start();
        t2.start();

    }
}
