package JDK8.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * 1.myRe = re.get() * re.get()内：如果re.get()无值会阻塞。
 * 2.future.complete(60);后会接触阻塞。
 * Created by chenyang on 2017/7/19.
 */

public class CompletableFutureComplete {
    public static class AskThread implements Runnable {
        CompletableFuture<Integer> re=null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe=0;
            try {
                System.out.println("child thread:before"+Thread.currentThread().getName()+"  status:"+re);
                myRe = re.get() * re.get();//future无值时，会阻塞
                Thread.sleep(5000);
                System.out.println("child thread:after"+Thread.currentThread().getName()+"  status:"+re);
            }catch (Exception e){
            }
            System.out.println(myRe);
        }
    }

    public static void main(String[] args) throws Exception{
        final CompletableFuture<Integer> future=new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        //模拟主线程的计算，然后触发re.get()
        Thread.sleep(100);
        System.out.println("main thread:before complete"+Thread.currentThread().getName()+"  status:"+future);
        future.complete(60);
//        System.out.println("future.get():"+future.get());
        System.out.println("main thread:after complete"+Thread.currentThread().getName()+"  status:"+future);
    }
}

