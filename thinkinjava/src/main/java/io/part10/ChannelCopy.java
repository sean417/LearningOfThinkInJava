package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutionException;

/**
 *
 * 两个FileChannle,一个用于读，一个用于写。
 *
 * Created by chenyang on 2016/10/21.
 */
public class ChannelCopy {
    private static final int BSIZE=1024;

    public static void main(String[] args) throws Exception{
        URL urlSrc = GetChannel.class.getResource("/data.txt" );

        URL urlDes = GetChannel.class.getResource("/data1.txt" );


        FileChannel
                in=new FileInputStream(urlSrc.getFile()).getChannel(),
                out=new FileOutputStream(urlDes.getFile()).getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        while (in.read(buffer)!=-1){//当read(buffer)!=-1时，表示我们已经到了输入的末尾。每次read(),会将数据输入到缓冲器中。
            buffer.flip();//准备缓冲器，以便ByteBuffer的信息可以供write（）提取。
            out.write(buffer);
            buffer.clear();//清空ByteBuffer。
        }
    }
}
