package io.part12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2016/10/29.
 */

class House implements Serializable{}

class Animal implements Serializable{
    private String name;
    private House preferredHouse;
    Animal(String nm,House h){
        name=nm;
        preferredHouse=h;
    }
    public String toString(){
        return name+"["+super.toString()+
                "], "+preferredHouse+"\n";
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        House house=new House();
        List<Animal> animals=new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog",house));
        animals.add(new Animal("Ralph the hamster",house));
        animals.add(new Animal("Molly the cat",house));
        print("animals: "+animals);
        ByteArrayOutputStream buf1=
                new ByteArrayOutputStream();
        ObjectOutputStream o1=new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);

        ByteArrayOutputStream buf2=
                new ByteArrayOutputStream();
        ObjectOutputStream o2=new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        ObjectInputStream in1=new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2=new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List animals1=(List)in1.readObject(),
                animals2=(List)in1.readObject(),
                animals3=(List)in2.readObject();
        print("animals1:"+animals1);
        print("animals2:"+animals2);
        print("animals3:"+animals3);
    }
}
