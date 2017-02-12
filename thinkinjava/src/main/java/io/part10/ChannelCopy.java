package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutionException;

/**
 * Created by chenyang on 2016/10/21.
 */
public class ChannelCopy {
    private static final int BSIZE=1024;

    public static void main(String[] args) throws Exception{
        FileChannel
                in=new FileInputStream("./src/main/java/io/source/data1.txt").getChannel(),
                out=new FileOutputStream("./src/main/java/io/source/data2.txt").getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        while (in.read(buffer)!=-1){
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
