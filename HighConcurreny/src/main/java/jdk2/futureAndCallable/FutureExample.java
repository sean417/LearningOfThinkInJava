package jdk2.futureAndCallable;

/*
 @author:   chenyang
 @date  2018/10/28 下午9:23

*/

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    Task task = new Task();
    Future<Integer> result = executor.submit(task);
    executor.shutdown();

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e1) {
        e1.printStackTrace();
    }

    System.out.println("主线程在执行任务");

    try {
        System.out.println("task运行结果"+result.get());
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }

    System.out.println("所有任务执行完毕");
}
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}

