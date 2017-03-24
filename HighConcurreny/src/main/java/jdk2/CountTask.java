package jdk2;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * forkjoinpool:
 * forkjoin采用了分而治之的思想。fork()是把子任务推进线程池，join()等待子任务完成。
 * 在ForkJoinPool里每个线程都有个等待执行的任务队列，不忙的线程会从忙的线程的等待任务队列的
 * 底部取任务执行，线程执行本任务队列时，从队列头取任务。避免的线程间的数据竞争。
 * Created by chenyang on 2017/3/22.
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD=10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    protected Long compute() {
        long sum=0;
        boolean canCompute=(end-start)<THRESHOLD;
        if(canCompute){
            for(long i=start;i<=end;i++){
                sum+=i;
            }
        }else {
            //分成100个小任务
            long step=(start+end)/100;
            ArrayList<CountTask> subTasks=new ArrayList<CountTask>();
            long pos=start;
            for(int i=0;i<100;i++){
                long lastOne=pos+step;
                if(lastOne>end)lastOne=end;
                CountTask subTask=new CountTask(pos,lastOne);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();//把子任务推向线程池
            }
            for(CountTask t:subTasks){
                sum+=t.join();//等待子任务结束，拿到子任务结果进行相加计算
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask task=new CountTask(0,200000L);
        ForkJoinTask<Long> result=forkJoinPool.submit(task);
        try {
            long res=result.get();
            System.out.println("sum="+res);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }


    }
}
