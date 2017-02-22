package ParallelBasic;

/**主线程中断子线程：
 * 主线程操作子线程t1.interrupt();然后子线程检查是否被中断的状态Thread.currentThread().interrupted()
 * 当为true时，break;
 * Created by chenyang on 2017/2/22.
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(new Runnable() {
            public void run() {
                while (true){
                    if(Thread.currentThread().interrupted()){
                        System.out.println("interrupt");
                        break;
                    }
                    Thread.yield();

                }
            }
        });

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
