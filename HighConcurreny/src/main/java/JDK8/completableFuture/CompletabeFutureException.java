package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * 捕捉异步线程的异常信息
 * Created by chenyang3@01zhuanche.com on 2017/7/21.
 */
public class CompletabeFutureException {
    public static Integer calc(Integer para){
        return para/0;
    }

    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> fu=CompletableFuture.supplyAsync(()->calc(50))
                .exceptionally(ex->{
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
