package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**CompletableFuture可以将多个CompletableFuture进行组合。
 * Created by chenyang3@01zhuanche.com on 2017/7/21.
 */
public class CompletableFutureCompose {

    public static Integer calc(Integer para){

        System.out.println(Thread.currentThread().getName());
        return para/2;
    }


    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> fu=
                CompletableFuture.supplyAsync(()->calc(50))
                        .thenCompose((i)->CompletableFuture.supplyAsync(()->calc(i)))
                        .thenApply((str)->"\""+str+"\"").thenAccept(System.out::println);
        fu.get();
    }
}
