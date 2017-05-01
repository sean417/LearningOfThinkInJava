package Lock;

/**
 * 一个锁的错误使用方式:
 *
 * 如果正确结果应该是200000，但是真实结果小于200000.
 * 原因：
 * Integer是一个不变类型，做i++变量本身不会变化。
 * 为什么i++能成功呢？因为做了拆箱和装箱的操作。装箱时会新建Integer对象，
 * 也就是说不同的线程的监视器（i）可能会不同。所以造成这个结果。
 * Created by chenyang on 2017/5/1.
 */
public class IntegerLock {
    static Integer i=0;
    public static class AddThread extends Thread{
        public void run(){
            for(int k=0;k<100000;k++){
                synchronized (i){
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        AddThread t1=new AddThread();
        AddThread t2=new AddThread();
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);

    }
}
