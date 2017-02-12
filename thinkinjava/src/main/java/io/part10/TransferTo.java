package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chenyang on 2016/10/21.
 */
public class TransferTo {

    public static void main(String[] args)throws Exception {
        FileChannel
                in=new FileInputStream("./src/main/java/io/source/data1.txt").getChannel(),
                out=new FileOutputStream("./src/main/java/io/source/data2.txt").getChannel();
        in.transferTo(0,in.size(),out);
    }
}
