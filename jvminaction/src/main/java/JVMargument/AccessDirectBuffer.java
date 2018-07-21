package JVMargument;

import java.nio.ByteBuffer;

/**
 *
 * 直接内存配置：
 * NIO在被广泛使用后，直接内存的使用变得非常普遍。直接内存跳过了java堆，使java程序可以直接访问原生堆空间，因此，从一定程度上加快了内存空间的访问速度。
 * 一般来说，直接内存的访问速度（读或者写）会快于堆内存。下面的代码统计了对直接内存和堆内存的读写速度。
 * @author chenyang30
 * @date 2018/7/14
 */
public class AccessDirectBuffer {
    public void directAccess(){
        long starttime=System.currentTimeMillis();
        ByteBuffer b=ByteBuffer.allocateDirect(500);
        for(int i=0;i<100000;i++){
            for(int j=0;j<99;j++){
                b.putInt(j);
            }
            b.flip();
            for(int j=0;j<99;j++){
                b.getInt();
            }
            b.clear();
        }
        long endtime=System.currentTimeMillis();
        System.out.println("testDirectWrite:"+(endtime-starttime));
    }

    public void bufferAccess(){
        long starttime=System.currentTimeMillis();
        ByteBuffer b=ByteBuffer.allocate(500);
        for(int i=0;i<100000;i++){
            for(int j=0;j<99;j++){
                b.putInt(j);
            }
            b.flip();
            for(int j=0;j<99;j++){
                b.getInt();
            }
            b.clear();
        }
        long endtime=System.currentTimeMillis();
        System.out.println("testBufferWrite:"+(endtime-starttime));
    }

    public static void main(String[] args) {
        AccessDirectBuffer alloc=new AccessDirectBuffer();
        alloc.bufferAccess();
        alloc.directAccess();

        alloc.bufferAccess();
        alloc.directAccess();
    }


}
