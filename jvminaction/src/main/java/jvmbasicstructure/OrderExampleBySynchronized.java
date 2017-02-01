package jvmbasicstructure;

/**
 * 同步后，即使做了writer重拍，因为互斥的缘故，
 * reader线程看writer线程也是顺序执行的。
 * 线程A:
 * flag=true
 * a=1
 * 线程B:
 * flag=true(此时a=1)
 *
 * 指令重排的基本原则：
 * 1.程序顺序原则：一个线程内保证语义的串行性。
 * 2.volatile规则：volatile变量的写，先发生于读。先执行写，，再执行读。
 * 3.锁规则：A先于，B先于，那么A必然先于C
 * 4.线程的start方法先于它的每一个动作。
 * 5.线程的所有操作先于线程的终结（Thread.join()）
 * 6.线程的中断（interrupt()），先于被中断线程的代码。
 * 7.对象的构造函数执行结束先于finalize()方法。
 *
 * Created by chenyang on 2017/1/26.
 */
public class OrderExampleBySynchronized {
    int a=0;
    boolean flag=false;

    public synchronized void writer(){
        a=1;
        flag=true;
    }
    public synchronized void reader(){
        if(flag){
            int i=a+1;
        }
    }
}
