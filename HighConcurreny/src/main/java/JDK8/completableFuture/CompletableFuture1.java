package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by chenyang3@01zhuanche.com on 2017/7/19.
 */

public class CompletableFuture1 {
    public static class AskThread implements Runnable {
        CompletableFuture<Integer> re=null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe=0;
            try {
                myRe = re.get() * re.get();
            }catch (Exception e){

            }
            System.out.println(myRe);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final CompletableFuture<Integer> future=new CompletableFuture<>();
        new Thread(new AskThread(future)).start();

//        Thread.sleep(100);

        future.complete(60);
    }
}

