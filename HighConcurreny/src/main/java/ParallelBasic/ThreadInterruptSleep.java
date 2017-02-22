package ParallelBasic;

/**中断案例二：
 * 子线程sleep时，主线程中断子线程：
 * 1.当catch (InterruptedException e)后，子线程的中断标志位会被重置。于是hread.currentThread().isInterrupted()为false
 * Created by chenyang on 2017/2/22.
 */
public class ThreadInterruptSleep {
    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(new Runnable() {
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted!");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("interruped when sleep");
                        System.out.println("this thread is interrupted after catch interrupt:"+Thread.currentThread().isInterrupted());
//                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        });

        t1.start();
        Thread.sleep(2000);
        System.out.println("this thread is interrupted in main thread before interrupt:"+t1.isInterrupted());

        t1.interrupt();
        System.out.println("this thread is interrupted in main thread after interrupt:"+t1.isInterrupted());

    }
}
