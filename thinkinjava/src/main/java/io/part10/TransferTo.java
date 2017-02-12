package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * 特殊方法transferTo和transferFrom()允许我们将一个通道和另一个通道直接相连：
 * Created by chenyang on 2016/10/21.
 */
public class TransferTo {

    public static void main(String[] args)throws Exception {
        URL urlSrc = GetChannel.class.getResource("/data.txt" );

        URL urlDes = GetChannel.class.getResource("/data1.txt" );

        FileChannel
                in=new FileInputStream(urlSrc.getFile()).getChannel(),
                out=new FileOutputStream(urlDes.getFile()).getChannel();
        in.transferTo(0,in.size(),out);
    }
}
