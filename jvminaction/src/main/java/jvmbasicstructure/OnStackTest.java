package jvmbasicstructure;

/**
 *
 * 第一种运行方式：-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
 * 这种方式new对象在栈上分配，gc不参与回收，因为变量仅仅在栈上分配空间，降低gc的工作量，同时防止堆上的空间被占用
 * 第二种运行方式：-server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC
    这种方式new对象在java堆上分配，gc参与释放
 * Created by chenyang on 2017/1/25.
 */
public class OnStackTest {
    public static void alloc(){
        byte[] b=new byte[2];
        b[0]=1;
    }

    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}
