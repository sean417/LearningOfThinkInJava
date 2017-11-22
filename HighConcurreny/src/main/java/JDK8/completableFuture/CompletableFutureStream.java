package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**流式调用：
 * 1.
 * Created by chenyang on 2017/7/21.
 */
public class CompletableFutureStream {
    public static Integer calc(Integer para){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException ex){
        }
        return para*para;
    }

    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> fu=CompletableFuture.supplyAsync(()->calc(50))
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
//        fu.get();
    }
}
