package io.part6;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by chenyang on 16/10/15.
 */
public class MemoryInput {
    public static void main(String[] args)
    throws IOException{
        StringReader in=new StringReader(
                BufferedInputFile.read("./src/main/java/io/source/hello.txt"));
        int c;
        while ((c=in.read())!=-1){
            System.out.print((char)c);
        }
    }
}
