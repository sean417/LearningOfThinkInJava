package jvmbasicstructure;

/**
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
 * Created by chenyang on 2017/1/31.
 */
public class OnStackTest2 {
    public static class User{
        public int id=0;
        public String name="";
    }
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
