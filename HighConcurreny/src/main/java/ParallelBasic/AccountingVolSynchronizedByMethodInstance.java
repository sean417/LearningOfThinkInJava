package ParallelBasic;

/**
 * synchronized第一种用法：
 * synchronized作用于一个实例方法，进入increase()前，线程必须获得当前对象实例的监视器，
 *
 *
 * Created by chenyang on 2017/2/25.
 */
public class AccountingVolSynchronizedByMethodInstance implements Runnable{
    static AccountingVolSynchronizedByMethodInstance instance=new AccountingVolSynchronizedByMethodInstance();
    static volatile int i=0;
    public synchronized void increase(){
        i++;
    }

    public void run() {
        for(int j=0;j<10000000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
