package ParallelBasic;

/**suspend()和resume():
 * suspend()执行时，线程处于挂起状态，而且线程不释放任何锁资源。
 * 如过resume()在suspend()前执行，会造成线程t2的挂起。
 * Created by chenyang on 2017/2/24.
 */
public class BadSuspend {
    public static Object u=new Object();
    static ChangeObjectThread t1=new ChangeObjectThread("t1");
    static ChangeObjectThread t2=new ChangeObjectThread("t2");


    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
//        Thread.sleep(1);
        t2.resume();

    }
}
