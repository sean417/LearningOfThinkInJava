package ParallelBasic;

/**
 * synchronized第三种用法：
 * synchronized作用于一个静态方法，进入increase()前，线程必须获得当前类的监视器，
 * 这样不同的线程对象实例共享这个类的监视器。
 *
 *
 * Created by chenyang on 2017/2/25.
 */
public class AccountingVolSynchronizedByMethodIClass implements Runnable{
    static AccountingVolSynchronizedByMethodIClass instance=new AccountingVolSynchronizedByMethodIClass();
    static volatile int i=0;
    public static synchronized void increase(){
        i++;
    }

    public void run() {
        for(int j=0;j<10000000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(new AccountingVolSynchronizedByMethodIClass());
        Thread t2=new Thread(new AccountingVolSynchronizedByMethodIClass());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
