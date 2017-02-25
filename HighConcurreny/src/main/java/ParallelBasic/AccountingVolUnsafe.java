package ParallelBasic;

/**
 * 没有用线程安全的方式共享数据，造成数据的混乱i++使得后一个操作收前一个影响。
 * Created by chenyang on 2017/2/25.
 */
public class AccountingVolUnsafe implements Runnable{
    static AccountingVolUnsafe instance=new AccountingVolUnsafe();
    static volatile int i=0;
    public static void increase(){
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
