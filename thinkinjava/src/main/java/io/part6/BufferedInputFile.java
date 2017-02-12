package io.part6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by chenyang on 16/10/15.
 */
public class BufferedInputFile {

    public static String
    read(String filename) throws IOException{
        BufferedReader in=new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb= new StringBuilder();
        while ((s=in.readLine())!=null){
            sb.append(s+"\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("./src/main/java/io/source/hello.txt"));
    }
}
