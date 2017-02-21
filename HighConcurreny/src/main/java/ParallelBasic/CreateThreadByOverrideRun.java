package ParallelBasic;

/**
 * 实现一个线程的方法一：
 * 直接覆盖override thread的run()方法。因为start();会在另一个线程里调用run();
 * Created by chenyang on 2017/2/21.
 */
public class CreateThreadByOverrideRun {
    public static void main(String[] args) {
        Thread t1=new Thread(){
            public void run(){
                System.out.println("hello,i am t1");
            }
        };
        t1.start();
    }
}
