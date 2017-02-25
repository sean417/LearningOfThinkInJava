package ParallelBasic;

/**
 * synchronized第一种方式的错误用法：
 * synchronized作用于一个相同的实例方法才有效，
 * 这里用了两个不同的实例（new了两次）,这样分别在两个对象实例上的监视器，互不干扰，
 * 造成数据的混乱
 *
 * Created by chenyang on 2017/2/25.
 */
public class AccountingVolSynchronizedByMethodInstanceBad implements Runnable{
    static AccountingVolSynchronizedByMethodInstanceBad instance=new AccountingVolSynchronizedByMethodInstanceBad();
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
        Thread t1=new Thread(new AccountingVolSynchronizedByMethodInstanceBad());
        Thread t2=new Thread(new AccountingVolSynchronizedByMethodInstanceBad());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
