package io.part6;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by chenyang on 16/10/15.
 */
public class FormattedMemoryInput {
    public static void main(String[] args)
    throws IOException{
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read("./src/main/java/io/source/hello.txt").getBytes()));
            while (true) {
                System.out.print((char) in.readByte());
            }
        }catch (EOFException e){
            System.err.println("End of stream");
        }

    }
}
