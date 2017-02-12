package io.part10;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2016/10/21.
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer){
        while (buffer.hasRemaining()){
            buffer.mark();
            char c1=buffer.get();
            char c2=buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data="UsingBuffers".toCharArray();
        ByteBuffer bb=ByteBuffer.allocate(data.length*2);
        CharBuffer cb=bb.asCharBuffer();
        cb.put(data);
        print(cb.rewind());

        symmetricScramble(cb);
        print(cb.rewind());

        symmetricScramble(cb);
        print(cb.rewind());
    }
}
