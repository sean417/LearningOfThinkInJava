package ParallelBasic;

/**线程间的协作：
 * 1.当在一个对象实例上调用wait()后，当前线程就会在这个对象上等待。
 * 2.当在一个对象实例上调用object.wait()后，当前线程就会进入object对象的等待队列。
 * 3.当object.notify()被调用时，它会从这个等待队列中随机唤醒一个线程，这个选择完全是随机的。
 * 4.object.notifyAll()唤醒等待队列中所有线程。
 * 5.无论是wait()还是notify()必须和synchronized语句内使用，都需要首先获得目标对象的一个监视器。
 * 6.object.wait()后，会释放这个监视器。
 * 7.object.notify()后,会唤醒等待队列的线程，唤醒的线程不是马上执行而是试图获得监视器（锁），然后才能执行。
 * 8.所以，执行nofity()的线程，必须释放监视器，等待对象的线程才能获得监视器。
 * Created by chenyang on 2017/2/22.
 */
public class SimpleWN {
    final static Object object=new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis()+"T1 wait for object ");
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"T1 end!");
            }
        }
    }



    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end!");

                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1=new T1();
        Thread t2=new T2();
        t1.start();
        t2.start();

    }
}
