package jvmbasicstructure;

/**
 * 同步后，即使做了writer重拍，因为互斥的缘故，
 * reader线程看writer线程也是顺序执行的。
 * 线程A:
 * flag=true
 * a=1
 * 线程B:
 * flag=true(此时a=1)
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
