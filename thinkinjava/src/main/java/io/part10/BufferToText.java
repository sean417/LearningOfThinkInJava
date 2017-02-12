package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by chenyang on 2016/10/21.
 *
 * 在GetChannel.java这个程序发现，为了输出文件中的信息，我们必须每次只读一个字节的数据，然后把每个byte类型
 * 强制转换为char类型。我们看一下java.nio.CharBuffer类，发现有个toString()这样定义的："返回一个包含缓冲器
 * 中所有字符的字符串。"可以用asCharBuffer()方法返回CharBuffer,但是并不能解决问题。
 *
 *
 */
public class BufferToText {
    private static final int BSIZE=1024;

    public static void main(String[] args) throws Exception{
        URL urlSrc = GetChannel.class.getResource("/data.txt" );

        URL urlDes = GetChannel.class.getResource("/data2.txt" );

        FileChannel fc=new FileOutputStream(urlDes.getFile()).getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();

        fc=new FileInputStream(urlSrc.getFile()).getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());//不能解决问题。

        //1.得到平台的默认字符集，然后用这个字符集解码。
        buffer.rewind();//返回到buffer的开始位置。
        String encoding=System.getProperty("file.encoding");
        System.out.println("Decoded using "+encoding+": "+ Charset.forName(encoding).decode(buffer));

        //2.在读文件时，使用指定的字符集编码，
        fc=new FileOutputStream(urlDes.getFile()).getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();

        fc=new FileInputStream(urlDes.getFile()).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());//asCharBuffer()默认用字符集UTF-16BE decode();

        fc=new FileOutputStream(urlDes.getFile()).getChannel();
        buffer=ByteBuffer.allocate(24);//char类型，1个char两个字节。"Some text"一共9个字节，多出来3个空白，也会打印出来。
        buffer.asCharBuffer().put("Some text");

        fc.write(buffer);
        fc.close();


        fc=new FileInputStream(urlDes.getFile()).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
