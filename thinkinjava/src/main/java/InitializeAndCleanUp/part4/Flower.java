package InitializeAndCleanUp.part4;

import static net.mindview.util.Print.print;

/**
 *
 * this用途3：在一个构造器中调用另一个构造器。
 * 同时注意：
 * 1.this调用构造器只能调用一次。
 * 2.在构造器之外编译器禁止调用构造器
 * 3.区分参数和数据成员。
 * Created by chenyang on 2017/1/2.
 */
public class Flower {
    int petalCount=0;
    String s="initial value";
    Flower(int petals){
        petalCount=petals;
        print("Contructor  w/ int arg only,petalCount="+petalCount);
    }
    Flower(String ss){
        print("Contructor  w/ String arg only,petalCount="+ss);
    }

    Flower(String s,int petals){
        this(petals);
        //this(s);//不能调用第二次
        this.s=s;//区分参数和数据成员。
        print("String & int args");
    }

    Flower(){
        this("hi",47);
        print("default constructor (no args)");
    }

    void printPetalCount(){
        //this(11)//不能在构造器外调用构造器
        print("petalCount="+petalCount+"s="+s);
    }

    public static void main(String[] args) {
        Flower x=new Flower();
        x.printPetalCount();
    }
}
