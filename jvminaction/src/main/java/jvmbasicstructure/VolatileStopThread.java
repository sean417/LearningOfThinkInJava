package jvmbasicstructure;

/**
 * volatile 不能代替锁
 * 一般认为volatile比锁性能好（不绝对）
 * 选择使用volatile的条件是：
 * 语义是否满足应用。
 * 没有volatile -server运行 无法停止，因为VolatileStopThread只在线程的本地存储区查看stop的值。
 *
 * 可见性：一个线程修改了变量，其他线程可以立即知道。
 * 保证可见性的方法：
 * 1.volatile
 * 2.synchronized(unlock之前，写变量值回主存)
 * 3.final（一旦初始化完成，其他线程就可见）。
 *
 * 有序性：
 * 1.在本线程内，操作都是有序的。
 * 2.在线程外观察，操作都是无序的。（指令重排或主内存同步延迟）
 *
 *
 * 指令重排：编译器为了使性能优化，编译器会将代码指令的顺序进行调整。保证线程内串行语义，不保证多线程直接的语义。如：
 * 写后读   a=1;b=a;    写一个变量后，再读这个位置。
 * 写后写   a=1;a=2;    写一个变量后，再写这个变量
 * 读后写   a=b;b=1;    读一个变量后，再读这个变量。
 * 以上语句不能重排。
 * 编译器不考虑多线程间的语义。
 * 可重拍：a=1;b=2;
 * Created by chenyang on 2017/1/26.
 */
public class VolatileStopThread extends  Thread {
    private volatile boolean stop=false;
    public void stepMe(){
        stop=true;
    }

    public void run(){
        int i=0;
        while (!stop){
            i++;
        }
        System.out.println("Stop thread");
    }

    public static void main(String[] args) throws InterruptedException{
        VolatileStopThread t=new VolatileStopThread();
        t.start();
        Thread.sleep(1000);
        t.stepMe();
        Thread.sleep(1000);
    }
}
