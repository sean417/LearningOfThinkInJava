package GcAlgorithm;

import java.lang.ref.SoftReference;

/**软引用：
 * 软引用是比强引用弱一点的引用类型，如果一个对象仅仅只有软引用，那么当堆空间不足时，
 * 就会被回收。
 *
 * 启动参数：-Xmx10m -Xms10m -XX:+UseSerialGC -XX:+PrintGCDetails -Xmn1m
 * Created by chenyang on 2017/2/2.
 */
public class SoftRef {
    public static class User{
        public User(int id,String name){
            this.id=id;
            this.name=name;
        }
        public int id;
        public String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        User u=new User(1,"geym");//强引用
        SoftReference<User> userSoftRef=new SoftReference<User>(u);//建立软引用
        u=null;//去除强引用

        System.out.println(userSoftRef.get());//软引用存在
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());//gc后软引用还存在，因为堆空间还是比较充足的。

        byte[] b=new byte[1024*924*7];//堆空间被其他对象占据，这是堆空间比较紧张
        System.gc();
        System.out.println(userSoftRef.get());//堆空间比较紧张时，软引用的对象被回收。
    }
}
