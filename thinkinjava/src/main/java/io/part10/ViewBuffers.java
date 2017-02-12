package io.part10;

import java.nio.*;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2016/10/21.
 */
public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer bb=ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        bb.rewind();
//        bb.flip();
        System.out.println("Byte Buffer");
        while (bb.hasRemaining()){
            System.out.println(bb.position()+" -> "+bb.get()+", ");
        }

        print();

        CharBuffer cb=
                ((ByteBuffer)bb.rewind()).asCharBuffer();
        System.out.println("Char Buffer ");
        while (cb.hasRemaining()){
            System.out.println(cb.position()+"->"+cb.get()+",");
        }
        print();

        FloatBuffer fb=
                ((ByteBuffer)bb.rewind()).asFloatBuffer();
        System.out.println("Float Buffer ");
        while (fb.hasRemaining()){
            System.out.println(fb.position()+"->"+fb.get()+",");
        }
        print();


        IntBuffer ib=
                ((ByteBuffer)bb.rewind()).asIntBuffer();
        System.out.println("Int Buffer ");
        while (ib.hasRemaining()){
            System.out.println(ib.position()+"->"+ib.get()+",");
        }
        print();


        LongBuffer lb=
                ((ByteBuffer)bb.rewind()).asLongBuffer();
        System.out.println("Long Buffer ");
        while (lb.hasRemaining()){
            System.out.println(lb.position()+"->"+lb.get()+",");
        }
        print();

        ShortBuffer sb=
                ((ByteBuffer)bb.rewind()).asShortBuffer();
        System.out.println("Short Buffer ");
        while (sb.hasRemaining()){
            System.out.println(sb.position()+"->"+sb.get()+",");
        }
        print();


        DoubleBuffer db=
                ((ByteBuffer)bb.rewind()).asDoubleBuffer();
        System.out.println("Double Buffer ");
        while (sb.hasRemaining()){
            System.out.println(db.position()+"->"+db.get()+",");
        }
        print();

    }
}
