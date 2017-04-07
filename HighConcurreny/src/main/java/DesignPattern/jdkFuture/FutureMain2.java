package DesignPattern.jdkFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by chenyang on 2017/3/26.
 */
public class FutureMain2 {
    public static void main(String[] args) throws Exception{
        ExecutorService executor= Executors.newFixedThreadPool(1);
        Future<String> future=executor.submit(new RealData("a"));

        System.out.println("请求完毕");
        System.out.println(        future.isDone());
        try {
            Thread.sleep(2000);
        }catch (InterruptedException ex){

        }
        System.out.println("数据="+future.get());
        System.out.println(future.isDone());

    }
}
