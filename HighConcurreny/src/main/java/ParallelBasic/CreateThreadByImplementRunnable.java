package ParallelBasic;

/**
 * 实现一个线程的方法二：
 * 实现一个Runnable接口，然后把这个实例当作Thread的构造方法参数传入Thread.
 * t1.start();后会另外启动一个线程执行实例的run()。
 * Created by chenyang on 2017/2/21.
 */
public class CreateThreadByImplementRunnable {
    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            public void run() {
                System.out.println("hello,i am t1");
            }
        });
        t1.start();
    }
}
