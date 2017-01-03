package InitializeAndCleanUp.part2;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * 方法接受较小的基本类型作为参数。如果传入的实际参数较大，就要通过类型转换来窄化转换。如果不这样做，编译器会报错
 * Created by chenyang on 2016/11/5 下午4:45.
 */
public class Demotion {
    void f1(char x){printnb("f1(char)");}
    void f1(byte x){printnb("f1(byte)");}
    void f1(short x){printnb("f1(short)");}
    void f1(int x){printnb("f1(int)");}
    void f1(long x){printnb("f1(long)");}
    void f1(float x){printnb("f1(float)");}
    void f1(double x){printnb("f1(double)");}

    void f2(byte x){printnb("f2(byte)");}
    void f2(short x){printnb("f2(short)");}
    void f2(int x){printnb("f2(int)");}
    void f2(long x){printnb("f2(long)");}
    void f2(float x){printnb("f2(float)");}
    void f2(double x){printnb("f2(double)");}


    void f3(short x){printnb("f3(short)");}
    void f3(int x){printnb("f3(int)");}
    void f3(long x){printnb("f3(long)");}
    void f3(float x){printnb("f3(float)");}
    void f3(double x){printnb("f3(double)");}

    void f4(int x){printnb("f4(int)");}
    void f4(long x){printnb("f4(long)");}
    void f4(float x){printnb("f4(float)");}
    void f4(double x){printnb("f4(double)");}

    void f5(long x){printnb("f5(long)");}
    void f5(float x){printnb("f5(float)");}
    void f5(double x){printnb("f5(double)");}

    void f6(float x){printnb("f6(float)");}
    void f6(double x){printnb("f6(double)");}

    void f7(double x){printnb("f7(double)");}

    void testConstVal(){
        printnb("5: ");
        f1(5);f2(5);f3(5);f4(5);f5(5);f6(5);f7(5);print();
    }

    void testChar(){
        char x='x';
        printnb("char: ");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);print();
    }
    void testByte(){
        byte x=0;
        printnb("byte: ");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);print();
    }
    void testShort(){
        short x=0;
        printnb("shor: ");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);print();
    }
    void testInt(){
        int x=0;
        printnb("int: ");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);print();
    }
    void testLong(){
        long x=0;
        printnb("long: ");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);print();
    }
    void testFloat(){
        float x=0;
        printnb("float: ");
        f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);print();
    }

    void testDouble(){
        double x=0;
        print("double argument: ");
        f1(x);f2((float) x);f3((long)x);f4((int)x);f5((short)x);f6((byte)x);f7((char)x);print();
    }

    public static void main(String[] args) {
        Demotion p=
                new Demotion();
        p.testDouble();
    }
}
