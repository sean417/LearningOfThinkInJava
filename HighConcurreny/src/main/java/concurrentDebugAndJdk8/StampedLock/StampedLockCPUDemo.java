package concurrentDebugAndJdk8.StampedLock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**readLock()的问题：
 * 1.当写锁没有释放时，读线程试图获取读锁，获取不到，状态为waiting()
 * 2.当线程被interrupt()时，读线程状态会变为runnable，快速消耗cpu资源。
 * 3.readLockInterruptibly()会对中断有处理。
 * Created by chenyang3@01zhuanche.com on 2017/8/6.
 */
public class StampedLockCPUDemo {

    static Thread[] holdCpuThreads=new Thread[3];
    static final StampedLock lock=new StampedLock();

    public static void main(String[] args) throws InterruptedException{
        new Thread(){
            @Override
            public void run() {
                long writeLong=lock.writeLock();
                LockSupport.parkNanos(600000000000L);
                lock.unlockWrite(writeLong);
            }
        }.start();
        Thread.sleep(100);
        for(int i=0;i<3;++i){
            holdCpuThreads[i]=new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }
        Thread.sleep(10000);

        //线程中断后，会占用CPU
        for(int i=0;i<3;++i){
            holdCpuThreads[i].interrupt();
        }
        int aa=0;
    }



    private static class HoldCPUReadThread implements Runnable{
        @Override
        public void run() {
            long lockr=lock.readLock();
            System.out.println(Thread.currentThread().getName()+" 获得读锁");
            lock.unlockRead(lockr);
        }
    }
}


