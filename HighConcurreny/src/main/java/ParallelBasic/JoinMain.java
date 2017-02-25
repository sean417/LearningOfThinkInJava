package ParallelBasic;

/**线程间的协作三：join()和yield()
 * join():join()和join(long millis):
 * 表示堵塞当前的调用线程让目标线程先走，然后调用线程跟着走，所以用join这个单词。join(long millis）表示
 * 当前线程的最大阻塞时间，当前线程等不及了往下走了。底层是调用了wait(),等待，然后被等待线程调用notifyAll()通知所有线程
 * 继续执行。
 * yield():表示当前线程让出CPU,不代表当前线程不执行，而是告诉系统，可以让别的线程优先获取cpu资源。
 *
 * Created by chenyang on 2017/2/25.
 */
public class JoinMain {

    public volatile static int i=0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0;i<10000000;i++);
        }
    }

    public static void main(String[] args)throws InterruptedException {
        AddThread at=new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }

}
