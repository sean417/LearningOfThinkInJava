package jdk2;

import java.util.concurrent.*;

/**通过override beforeExecute，afterExecute，terminated，监控线程池执行的细节
 * Created by chenyang on 2017/3/19.
 */
public class ExtThreadPool {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService es=new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()){
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
            @Override
            protected void beforeExecute(Thread t,Runnable r){
                System.out.println("准备执行："+((ThreadPoolDemo.MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成："+((ThreadPoolDemo.MyTask)r).name);
            }
        };

        for(int i=0;i<5;i++){
            ThreadPoolDemo.MyTask task=new ThreadPoolDemo.MyTask("TASK-GEYM-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
