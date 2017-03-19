package jdk2;

import java.util.concurrent.*;

/**
 * 针对ThreadPoolExecutor的拒绝接口自己实现一个拒绝策略的接口。
 * Created by chenyang on 2017/3/19.
 */
public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable{
        public void run() {
            System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyTask task=new MyTask();
        ExecutorService es=new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+"is discard");
                    }
                });
        for(int i=0;i<Integer.MAX_VALUE;i++){
            es.submit(task);
            Thread.sleep(10);
        }
    }
}
