package io.part12;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by chenyang on 2016/10/25.
 */
public class ThawAlien {
    public static void main(String[] args) throws Exception{
        ObjectInputStream in=new ObjectInputStream(
                new FileInputStream(new File("./src/main/java/io/part12/X.file")));
        Object mystery=in.readObject();
        System.out.println(mystery.getClass());
    }
}
