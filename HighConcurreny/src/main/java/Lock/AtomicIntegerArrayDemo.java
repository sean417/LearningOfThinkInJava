package Lock;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**对于数组等的无锁，可以用的原子数组有：
 * AtomicIntegerArray,AtomicLongArray,AtomicReferenceArray。
 * Created by chenyang on 2017/2/28.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray arr=new AtomicIntegerArray(10);
    public static class AddThread implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                arr.getAndIncrement(k%arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] ts=new Thread[10];
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread());
        }
        for(int k=0;k<10;k++){
            ts[k].start();
        }
        for (int k=0;k<10;k++){
            ts[k].join();
        }
        System.out.println(arr);
    }
}
