package io.part8;

import java.io.*;

/**
 * Created by chenyang on 16/10/17.
 */
public class Redirecting {
    public static void main(String[] args)
    throws IOException{
        PrintStream console=System.out;
        BufferedInputStream in=new BufferedInputStream(
                new FileInputStream("./src/main/java/io/source/data.txt")
        );

        PrintStream out=new PrintStream(
                new FileOutputStream("./src/main/java/io/source/test.out")
        );
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null){
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
