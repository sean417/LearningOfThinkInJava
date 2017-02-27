package Lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对应AtomicIntegerDemo性能对比，加锁明显性能不好。加锁会有线程阻塞被挂起的
 * 状态，浪费CPU 8万个时钟周期。
 *
 * Created by chenyang on 2017/2/27.
 */
public class AtomicIntegerVSlockDemo {
    static int i;
    public static class AddThread implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                synchronized (AddThread.class) {
                    i++;
                }
            }
        }
        public static void main(String[] args) throws InterruptedException{
            Long startTime=System.currentTimeMillis();
            Thread[] ts=new Thread[10];
            for(int k=0;k<10;k++){
                ts[k]=new Thread(new AddThread());
            }
            for(int k=0;k<10;k++){
                ts[k].start();
            }
            for(int k=0;k<10;k++){
                ts[k].join();
            }
            System.out.println(i);
            Long endTime=System.currentTimeMillis();
            System.out.println(endTime-startTime);

        }
    }
}
