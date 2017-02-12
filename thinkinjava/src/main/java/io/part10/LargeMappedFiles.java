package io.part10;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by chenyang on 2016/10/22.
 */
public class LargeMappedFiles{
    static int length=0x8FFFFFF;

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out =
                new RandomAccessFile("./src/main/java/io/source/data.txt","rw").getChannel().map(FileChannel.MapMode.READ_WRITE,0,length);
        for(int i=0;i<length;i++){
            out.put((byte)'x');
        }
        print("Finished Write");
        for(int i=length/2;i<length/2+6;i++){
            printnb((char)out.get(i));
        }
    }
}
