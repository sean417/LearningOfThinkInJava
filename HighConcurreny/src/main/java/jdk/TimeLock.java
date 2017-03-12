package jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**ReentrantLock的可限时：tryLock（timeout,timeunit）
 * 如果在限时的时间范围内，拿到锁就返回true,否则返回false。而且线程在等待锁资源过程中是可中断的。
 * Created by chenyang on 2017/3/12.
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                    Thread.sleep(6000);
            }else {
                System.out.println("get lock failed");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){//如何没有这句控制可能会造成没有拿到监视器的线程释放监视器，从而引起IllegalMonitorStateException
            lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        TimeLock tl1=new TimeLock();
        Thread t1=new Thread(tl1);
        Thread t2=new Thread(tl1);
        t1.start();
        t2.start();
//        Thread.sleep(4000);可中断
//        t2.interrupt();

    }
}
