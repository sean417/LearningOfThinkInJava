package io.part6;

import java.io.*;

/**
 * Created by chenyang on 16/10/15.
 */
public class BasicFileOutput {
    static String file="./src/main/java/io/source/hello.txt";

    public static void main(String[] args)
    throws IOException{
        BufferedReader in=new BufferedReader(
                new StringReader(BufferedInputFile.read(file)));
        PrintWriter out=new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount=1;
        String s;
        while ((s=in.readLine())!=null){
            out.println(lineCount++ +":"+s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
