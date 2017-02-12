package io.part12;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by chenyang on 2016/10/25.
 */
public class FreezeAlien {
    public static void main(String[] args) throws Exception {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("./src/main/java/io/part12/X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
