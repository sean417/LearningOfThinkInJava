package JVMargument;

/**
 * Created by chenyang on 2017/1/28.
 */
public class HeapAlloc {
    public static void main(String[] args) {
        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+"bytes");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()+"bytes");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()+"bytes");

        byte[] b=new byte[1*1024*1024];
        System.out.println("分配了1M空间给数组");


        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+"bytes");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()+"bytes");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()+"bytes");

        byte[] c=new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");


        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+"bytes");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()+"bytes");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()+"bytes");
        System.gc();
        System.out.println("gc释放空间，空闲空间增多");
        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+"bytes");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()+"bytes");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()+"bytes");
    }
}
