package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**通过supplyAsync()实现模拟future的阻塞功能。
 * 1.supplyAsync()会创建一个线程执行calc()方法。
 * 2.supplyAsync()会立即构造CompletableFuture成功，并立即返回不影响主线程。
 * 3.future.get()如果子线程没执行完calc(),会阻塞。
 * 4.CompletableFuture.supplyAsync()默认用的是ForkJoinPool.commonPool()方法，获得一个ForkJoin线程池。这个线程池
 * 内的线程都是Daemon线程，如果主线程退出，这些线程无论执行完了没有都会退出系统。
 * Created by chenyang on 2017/7/20.
 */
public class CompletableFutureSupplyAsync {
    public static Integer calc(Integer para){
        try {
            Thread.sleep(3000);
            int i=0;//打断点
        }catch (InterruptedException ex){
        }
        return para*para;
    }

    public static void main(String[] args) throws Exception{
        final CompletableFuture<Integer> future=
                CompletableFuture.supplyAsync(()->calc(50));//Daemon线程池
        System.out.println("main thread process");

        Thread.sleep(4000);
        System.out.println("future.get() before");
        System.out.println(future.get());
        System.out.println("future.get() after");
    }
}
