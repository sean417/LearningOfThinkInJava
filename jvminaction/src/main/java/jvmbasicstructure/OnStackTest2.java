package jvmbasicstructure;

/**
 * java 8 jvm参数默认打开逃逸分析DoEscapeAnalysis
 * 栈上分配是java虚拟机提供的一项优化技术，它的基本思想是，对于那些线程私有的对象
 * （这里指不可能被其他线程访问的对象），可以将他们打散在栈上，而不是分配在堆上。
 * 分配在栈上好处是可以在函数调用结束后自行销毁，而不需要垃圾回收器的介入，从而
 * 提高系统的性能。
 * 栈上分配的一个技术基础是进行逃逸分析。逃逸分析的目的是判断对象的作用域是否有
 * 可能逃逸出函数体。如下代码显示了一个逃逸对象：
 * private static User u;
 * public static void alloc(){
 *     u=new User();
 *     u.id=5;
 *     u.name="geym";
 * }
 * 非逃逸对象：
 * public static void alloc(){
 *     User u=new User;
 *     u.id=5;
 *     u.name="geym";
 * }
 *
 * 1.堆上分配-server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC
 * 输出结果：
 *   。。。。。。。
 *   [GC (Allocation Failure)  2504K->456K(9728K), 0.0003244 secs]
 [GC (Allocation Failure)  2504K->456K(9728K), 0.0003120 secs]
 [GC (Allocation Failure)  2504K->456K(9728K), 0.0004905 secs]
 [GC (Allocation Failure)  2504K->456K(9728K), 0.0003718 secs]
 [GC (Allocation Failure)  2504K->456K(9728K), 0.0003444 secs]
 1815
 1815毫秒完成

    2.栈上分配：-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
    输出结果：
    12

    12毫秒完成
 *
 *
 * 逃逸：当一个对象的指针被多个方法或线程引用时，我们称这个指针发生了逃逸，一般情况返回对象、对全局变量的赋值一般都会发生逃逸。
 *
 * 逃逸分析：用来分析这种逃逸现象的方法称为逃逸分析
 *
 *逃逸分析优化-栈上分配：栈上分配的意思是方法内局部变量（未发生逃逸）生成的实例在栈上分配，不用在堆中分配，分配完成后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。


 1.小对象（一般几十个bytes）,在没有逃逸的情况下，可以直接分配债栈上。
 2.直接分配在栈上，可以自动回收，减轻GC压力。
 3.大对象或者逃逸对象无法栈上分配。
 * Created by chenyang on 2017/1/31.
 */
public class OnStackTest2 {
    public static class User{
        public int id=0;
        public String name="";
    }
//    static User u;
    public static void alloc(){
        User u=new User();
        u.id=5;
        u.name="geyrtyrtyututm";
    }

    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}
