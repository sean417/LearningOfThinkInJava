package Lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * incrementAndGet()：加一同时返回当前值。
 * AtomicInteger这个采用CAS操作实现线程安全，比用锁这样的方式保证线程安全的性能要高
 * 参见AtomicIntegerVSlockDemo
 * Created by chenyang on 2017/2/27.
 */
public class AtomicIntegerDemo {
    static AtomicInteger i=new AtomicInteger();
    public static class AddThread implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                i.incrementAndGet();
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
