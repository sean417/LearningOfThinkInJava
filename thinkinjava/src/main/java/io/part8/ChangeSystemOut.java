package io.part8;

import java.io.PrintWriter;

/**
 * Created by chenyang on 16/10/16.
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter out=new PrintWriter(System.out,true);
        out.println("hello ");
    }
}
