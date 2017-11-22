package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**CompletableFuture可以将多个CompletableFuture进行组合。
 * Created by chenyang on 2017/7/21.
 */
public class CompletableFutureCombine {

    public static Integer calc(Integer para){

        System.out.println(Thread.currentThread().getName());
        return para/2;
    }


    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> intFuture=CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Integer> intFuture2=CompletableFuture.supplyAsync(()->calc(25));

        CompletableFuture<Void> fu= intFuture.thenCombine(intFuture2,(i,j)->(i+j))
                        .thenApply((str)->"\""+str+"\"")
                        .thenAccept(System.out::println);
        fu.get();
    }
}
