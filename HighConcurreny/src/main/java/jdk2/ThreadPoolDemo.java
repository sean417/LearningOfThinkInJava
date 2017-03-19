package jdk2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程数量的线程池。
 * Created by chenyang on 2017/3/19.
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable{
        public void run() {
            System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task=new MyTask();
        ExecutorService es= Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            es.submit(task);
        }
    }
}
