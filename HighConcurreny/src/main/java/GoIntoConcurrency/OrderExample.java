package GoIntoConcurrency;

/**三。有序性：
 * 有序性问题的原因是因为程序在执行时，可能进行指令重排，重拍后的指令和原指令的顺序未必一致。
 *
 * 指令重拍的原因是，cup执行汇编指令时，用的是流水线，每个汇编指令有好几步，如，IF,ID,EX,MEM,WB。
 * 当上一个汇编指令的特定的一步没完，下一个汇编指令的一步无法执行执行。造成流水线停顿。
 * 为了最大限度避免流水线停顿，于是产生指令重排，避免流水线停顿。
 *
 * Happen-before原则：
 * 1.程序顺序原则：一个线程内包装语义的串行性。
 * 2.volatile规则:volatile变量的写,先发于读，这保证了volatile变量的可见性。
 * 3.锁规则：解锁（unlock）必然发生在随后的加锁（lock）前。
 * 4.传递性：A先于B,B先于C,那么A必然先于C。
 * 5.线程的start()方法先于它的每个动作。
 * 6.线程的所有操作先于线程的终结（Thread.join()）。
 * 7.线程的中断(interrupt())先于被中断线程的代码。
 * 8.对象的构造函数执行，结束先于finalize()方法。
 * 9.
 * 10.
 * Created by chenyang on 2017/2/20.
 */
public class OrderExample {
    int a=0;
    boolean flag=false;
    public void writer(){
        a=1;
        flag=true;//这两个语句的执行顺序真正执行时，可能会发生指令重排，执行顺序变为：flag=true;a=1;
    }

    public void reader(){
        if(flag){
            int i=a+1;
        }
    }
}
