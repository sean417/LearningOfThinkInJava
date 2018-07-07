package JVMargument;

/**
 * 1.-Xmx20m -Xms20m -Xmn1m -XX:+PrintGCDetails:
 * [GC (Allocation Failure) [PSYoungGen: 499K->400K(1024K)] 499K->400K(19968K), 0.0013969 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 Heap
 PSYoungGen      total 1024K, used 742K [0x00000007bfe80000, 0x00000007c0000000, 0x00000007c0000000)
 eden space 512K, 66% used [0x00000007bfe80000,0x00000007bfed5af8,0x00000007bff00000)
 from space 512K, 78% used [0x00000007bff00000,0x00000007bff64010,0x00000007bff80000)
 to   space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
 ParOldGen       total 18944K, used 10240K [0x00000007bec00000, 0x00000007bfe80000, 0x00000007bfe80000)
 object space 18944K, 54% used [0x00000007bec00000,0x00000007bf6000a0,0x00000007bfe80000)
 Metaspace       used 2942K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 322K, capacity 386K, committed 512K, reserved 1048576K
 *   1)没有GC发生。
 *   2)全部分配在老年代.
 *
 * 2.-Xmx20m -Xms20m -Xmn15m -XX:+PrintGCDetails:
 *   Heap
 PSYoungGen      total 13824K, used 11969K [0x00000007bf100000, 0x00000007c0000000, 0x00000007c0000000)
 eden space 12288K, 97% used [0x00000007bf100000,0x00000007bfcb04e8,0x00000007bfd00000)
 from space 1536K, 0% used [0x00000007bfe80000,0x00000007bfe80000,0x00000007c0000000)
 to   space 1536K, 0% used [0x00000007bfd00000,0x00000007bfd00000,0x00000007bfe80000)
 ParOldGen       total 5120K, used 0K [0x00000007bec00000, 0x00000007bf100000, 0x00000007bf100000)
 object space 5120K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf100000)
 Metaspace       used 2966K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 326K, capacity 386K, committed 512K, reserved 1048576K
 * 1) 没有触发GC。
 * 2) 全部分配在eden。
 * 3) 老年代没有使用。
 *
 *
 * 3.-Xmx20m -Xms20m -Xmn7m -XX:+PrintGCDetails
 *[GC (Allocation Failure) [PSYoungGen: 5234K->496K(6656K)] 5234K->1528K(19968K), 0.0011550 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 [GC (Allocation Failure) [PSYoungGen: 5796K->448K(6656K)] 6828K->2504K(19968K), 0.0012063 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
 Heap
 PSYoungGen      total 6656K, used 1696K [0x00000007bf900000, 0x00000007c0000000, 0x00000007c0000000)
 eden space 6144K, 20% used [0x00000007bf900000,0x00000007bfa38318,0x00000007bff00000)
 from space 512K, 87% used [0x00000007bff80000,0x00000007bfff0020,0x00000007c0000000)
 to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
 ParOldGen       total 13312K, used 2056K [0x00000007bec00000, 0x00000007bf900000, 0x00000007bf900000)
 object space 13312K, 15% used [0x00000007bec00000,0x00000007bee02020,0x00000007bf900000)
 Metaspace       used 2912K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 319K, capacity 386K, committed 512K, reserved 1048576K
 1）
 2）
 3）
3.-Xmx20m -Xms20m -Xmn7m -XX:SurviviorRadio=1 -XX:+PrintGCDetails

 -XX:SurvivorRatio=eden/from=eden/
 * Created by chenyang on 2017/1/30.
 */
public class NewSizeDemo {
    public static void main(String[] args) {
        byte[] b=null;
        for(int i=0;i<10;i++){
            b=new byte[1*1024*1024];
            try {
//                Thread.sleep(1000);
            }catch (Exception ex){

            }
        }
    }
}
