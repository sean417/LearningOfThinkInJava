package ParallelBasic;

/**
 * 守护线程：
 * 守护线程不会因为没有结束,虚拟机就不停止。也就是说，应用程序的线程完成后，守护线程自动终止。
 * 如：GC,JIT等都是守护线程。
 * Created by chenyang on 2017/2/25.
 */
public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t=new DaemonT();
        t.setDaemon(true);
        t.start();


    }
}
