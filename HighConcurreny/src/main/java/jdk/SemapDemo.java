package jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semapore是对锁的扩充，也称共享锁：
 * 允许多个线程同时进入临界区。
 * 先定义Semapore有多少个许可，每个线程要申请一个许可成功后才能进入。
 * 许可消费完，其他线程再申请只能进入等待队列。
 * acquire()申请许可。触发获得许可否则阻塞，进入等待队列。同时可以被中断
 * tryAcquire()尝试申请许可并立即返回，成功或失败。
 * acquireUninterruptibly():申请许可同时不能被中断。
 * Created by chenyang on 2017/3/12.
 */
public class SemapDemo implements Runnable {
    final Semaphore semp=new Semaphore(5);
    public void run() {
        try {
            semp.acquire();//semp.acquire(2)可以获得多个许可。

            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semp.release();//释放许可  semp.release(2)可以释放多个许可。
        }
    }

    public static void main(String[] args) {
        ExecutorService exec= Executors.newFixedThreadPool(20);
        final SemapDemo demo=new SemapDemo();
        for (int i=0;i<20;i++){
            exec.submit(demo);
        }
    }
}
