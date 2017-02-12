package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by chenyang on 2016/10/21.
 */
public class BufferToText {
    private static final int BSIZE=1024;

    public static void main(String[] args) throws Exception{
        FileChannel fc=new FileOutputStream("./src/main/java/io/source/data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();

        fc=new FileInputStream("./src/main/java/io/source/data.txt").getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());


        buffer.rewind();
        String encoding=System.getProperty("file.encoding");
        System.out.println("Decoded using "+encoding+": "+ Charset.forName(encoding).decode(buffer));

        //or
        fc=new FileOutputStream("./src/main/java/io/source/data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();

        fc=new FileInputStream("./src/main/java/io/source/data.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        fc=new FileOutputStream("./src/main/java/io/source/data4.txt").getChannel();
        buffer=ByteBuffer.allocate(24);
        buffer.asCharBuffer().put("Some text");
        fc.write(buffer);
        fc.close();


        fc=new FileInputStream("./src/main/java/io/source/data4.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
