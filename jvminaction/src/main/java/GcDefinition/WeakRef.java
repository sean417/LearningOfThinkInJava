package GcDefinition;

import java.lang.ref.WeakReference;

/**
 * 弱引用是一种比软引用较弱的引用类型。在系统gc时，只要发现弱引用，不管系统堆空间使用情况如何，
 * 都会将对象进行回收。但是，由于垃圾回收期的线程通常优先级很低，并不一定很快发现持有的弱引用对象。
 * 软引用和弱引用非常适合那些可有可无的缓存数据。当系统堆内存很低时，系统回收。当系统堆内存很高时，
 * 这些缓存又能存在很长时间，从而起到加速系统的作用。
 * Created by chenyang on 2017/2/2.
 */
public class WeakRef {
    public static class User {
        public User(int id, String name) {
        this.id=id;
        this.name=name;
        }
        public int id;
        public String name;
    }

    public static void main(String[] args) {
        User u=new User(1,"geym");
        WeakReference<User> userWeakRef=new WeakReference<User>(u);
        u=null;
        System.out.println(userWeakRef.get());
        System.gc();
        //不管当前内存空间是否够用，都会回收它的内存
        System.out.println("After GC:");
        System.out.println(userWeakRef.get());
    }

}
