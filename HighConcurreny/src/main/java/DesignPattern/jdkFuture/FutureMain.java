package DesignPattern.jdkFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by chenyang on 2017/3/26.
 */
public class FutureMain {
    public static void main(String[] args) throws Exception{
        FutureTask<String> future=new FutureTask<String>(new RealData("a"));
        ExecutorService executor= Executors.newFixedThreadPool(1);

        executor.submit(future);

        System.out.println("请求完毕");

        try {
            Thread.sleep(2000);
        }catch (InterruptedException ex){

        }

        System.out.println("数据="+future.get());
    }
}
