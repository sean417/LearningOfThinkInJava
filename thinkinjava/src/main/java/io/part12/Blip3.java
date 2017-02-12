package io.part12;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2016/10/25.
 */
public class Blip3 implements Externalizable{
    private int i;
    private String s;
    public Blip3(){
        print("Blip3 Constructor");
    }

    public Blip3(String x,int a){
        print("Blip3(String x,int a)");
        s=x;
        i=a;
    }
    public String toString(){return s+i;}

    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip3.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip3.readExternal");
        s=(String)in.readObject();
        i=in.readInt();
    }

    public static void main(String[] args) throws IOException,ClassNotFoundException{
        print("Constructing objects:");
        Blip3 b3=new Blip3("A String",47);
        print(b3);

        ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream("./src/main/java/io/source/Blips.out"));

        print("Saving object");
        o.writeObject(b3);
        o.close();
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("./src/main/java/io/source/Blips.out"));
        print("Recoving b3:");
        b3=(Blip3)in.readObject();
        print(b3);
    }
}
