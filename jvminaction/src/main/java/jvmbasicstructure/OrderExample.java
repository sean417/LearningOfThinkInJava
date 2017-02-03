package jvmbasicstructure;

/**
 * 指令重排--破坏线程间的有序性
 *
 * 线程A首先执行writer()方法
 * 线程B线程接着执行reader()方法。
 * 线程B在int i=a+1 是不一定能看到a已经被赋值为1.
 * 因为在writer中，这两句顺序可能打乱。
 *
 * 线程A:
 * flag=true;
 * a=1;
 *
 * 线程B:
 * flag=true(此时a=0)
 * Created by chenyang on 2017/1/26.
 */
public class OrderExample {
    int a=0;
    boolean flag=false;
    public void writer(){
        a=1;
        flag=true;
    }

    public void reader(){
        if(flag){
            int i=a+1;
            //.......
        }
    }
}
