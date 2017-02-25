package ParallelBasic;

/**实现一个安全的suspend()和resume():
 * 定义了一个是否被挂起的标记：suspendme。
 *
 * Created by chenyang on 2017/2/25.
 */
public class GoodSuspend {
    public static Object u=new Object();
    public static class ChangeObjectThread extends Thread{
        volatile boolean suspendme=false;
        public void suspendMe(){
            suspendme=true;
        }

        public void resumeMe(){
            suspendme=false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (suspendme){
                        try {
                            wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (u){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ChangeObjectThread t1=new ChangeObjectThread();
        ChangeObjectThread t2=new ChangeObjectThread();
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.suspendMe();
        System.out.println("suspend t1 2 sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();

    }
}
