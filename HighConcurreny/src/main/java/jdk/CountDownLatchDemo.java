package jdk;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**CountDownLatch:这个工具可以控制线程等待像是一个栅栏，它可以让某一个线程等待直到倒计时结束，再开始执行。
 * 首先定义十个准备工作的CountDownLatch，完成一个就数一个，end.countDown();
 * 主线程等待十个都完成，end.await();然后在往下走。
 * Created by chenyang on 2017/3/12.
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end=new CountDownLatch(10);

    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exec= Executors.newFixedThreadPool(10);
        CountDownLatchDemo demo1=new CountDownLatchDemo();
        for(int i=0;i<10;i++){
            exec.submit(demo1);
        }

        end.await();

        System.out.println("Fire!");
        exec.shutdown();
    }
}
