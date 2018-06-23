package JVMargument;

/**
 *
 * -Xmx20m -Xms5m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 * Created by chenyang on 2017/1/28.
 */
public class HeapAlloc {
    public static void main(String[] args) {


        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"m");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"m");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"m");

        byte[] b=new byte[1*1024*1024];
        System.out.println("分配了1M空间给数组");


        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"m");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"m");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"m");

        byte[] c=new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");


        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"m");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"m");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"m");
        System.gc();
        System.out.println("gc释放空间，空闲空间增多");
        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"m");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"m");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"m");
    }
}
