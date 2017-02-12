package io.part11;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by chenyang on 2016/10/23.
 */
public class GZIPcompress {
    public static void main(String[] args) throws IOException{
        BufferedReader in=new BufferedReader(
                new FileReader("./src/main/java/io/source/data1.txt")
        );
        BufferedOutputStream out=new BufferedOutputStream(new GZIPOutputStream(
                new FileOutputStream("./src/main/java/io/source/data1.gz")
        ));

        System.out.println("Writing file");

        int c;
        while ((c=in.read())!=-1){
            out.write(c);
        }
        in.close();
        out.close();
        System.out.println("Reading file");
        BufferedReader in2=new BufferedReader(
                new InputStreamReader(new GZIPInputStream(
                        new FileInputStream("./src/main/java/io/source/data1.gz"))));
        String s;
        while ((s=in2.readLine())!=null){
            System.out.println(s);
        }

    }
}
