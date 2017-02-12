package io.part6;

import java.io.*;

/**
 * Created by chenyang on 16/10/15.
 */
public class StoringAndRecoveringData {
    public static void main(String[] args)
    throws IOException{
        DataOutputStream out=new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("./src/main/java/io/source/data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();

        DataInputStream in=new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("./src/main/java/io/source/data.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());

    }
}
