package io.part12;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2016/10/27.
 */
public class Logon implements Serializable {
    private Date data=new Date();
    private String username;
    private transient String password;

    public Logon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Logon{" +
                "data=" + data +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception{
        Logon a=new Logon("Hulk","myLittlePony");
        print("logon a="+a);
        ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream("./src/main/java/io/source/Logon.out"));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1);
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("./src/main/java/io/source/Logon.out"));

        print("Recovering object at "+new Date());
        a=(Logon)in.readObject();
        print("logon a="+a);
    }
}
