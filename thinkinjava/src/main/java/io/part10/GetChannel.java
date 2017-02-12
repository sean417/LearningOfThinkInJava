package io.part10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio相对于IO优势在于速度，因为使用的结构更接近于操作系统执行IO的方式：通道和缓冲器。
 * nio分为文件NIO和网络的NIO，这里只研究文件NIO。
 * 我们可以把它想成一个煤矿，通道是一个包含煤层（数据）的矿藏，而缓冲器是派送到矿藏的卡车。
 * 卡车满载而归，我们再从卡车上获得煤炭。也就是说，我们没直接跟通道联系，我们只和缓冲器交互，
 * 并把缓存器送到通道。通道要么从缓冲器获得数据，要么向缓冲器发送数据。
 *
 * ByteBuffer类：
 * 唯一直接与通道交互的缓存器是ByteBuffer,通过告知分配多少存储空间来创建一个ByteBuffer对象，有个方法选择集，
 * 用于以原始的字节形式（put()）或基本数据类型（putChar(),putDouble(),putFloat()等）输出和读取对象,
 * 但是无法输出或读取对象，即使是字符串对象也不行。这种处理比较底层，正好是大多数操作系统中更有效的映射方式。
 *
 * FileChannel:
 * 旧的IO类库中有三个类产生FileChannel,分别是，FileInputStream,FileOutputStream,以及既读又写的RandomAccessFile。
 * 注意这些流与底层的nio性质一致。Reader和Writer这种字符模式类不能用于产生通道。java.nio.channels类提供了实用方法，
 * 用以在通道中产生Reader和Writer。
 * Created by chenyang on 2016/10/21.
 */
public class GetChannel {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws Exception {


        URL url = GetChannel.class.getResource("/data.txt" );
        FileChannel fc=
                new FileOutputStream(url.getFile()).getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));//使用wrap()方法将已经存在的字节数组包装到ByteBuffer中
        fc.close();

        fc=new RandomAccessFile(url.getFile(),"rw").getChannel();
        fc.position(fc.size());//我们可以在文件内随处移动FileChannel,在这里移动到最后，以便附加其他操作。
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();


        fc=new FileInputStream(url.getFile()).getChannel();//对于只读操作，用静态的allocate（）方法分配ByteBuffer
        ByteBuffer buff=ByteBuffer.allocate(BSIZE);//nio的目标是快速移动数据，ByteBuffer的大小的设定很重要，一般大于1k,根据实际情况修改。
        fc.read(buff);//告知FileChannel向ByteBuffer存储字节。
//        System.out.printf(buff.getClass().getName());
        buff.flip();//一旦调用read()告知FileChannel向ByteBuffer存储字节,就必须调用缓冲器上的flip(),让ByteBuffer做好
                    //让别人读的准备（另一个让ByteBuffer做好读的准备方法是调用clare(),例子在ChannelCopy类里）
        while (buff.hasRemaining()){
            System.out.print((char)buff.get());
        }

    }

}
