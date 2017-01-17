package InitializeAndCleanUp.part6;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2017/1/17.
 * 1.java默认的初始化。
 * java尽力保证：所有变量在使用前都得得到恰当的初始化。
 * 1.对于方法的局部变量，java以编译时错误的形式来贯彻这种保证
 * 2.对于类的数据成员（即字段）是基本类型，如果没有值，Java会给一个合适的默认值
 * 3.char的值是0，所有事空白。
 */
public class InitialValues {
    boolean t;
    char c;
    byte b;
    short s;
    int i;
    long l;
    float f;
    double d;
    InitialValues reference;
    void printInitialValues(){
        print("Data type     Initial value");
        print("boolean       "+t);
        print("char          "+c);
        print("byte          "+b);
        print("short         "+s);
        print("int           "+i);
        print("long          "+l);
        print("float         "+f);
        print("double        "+d);
        print("reference     "+reference);
    }

    public static void main(String[] args) {
        InitialValues iv=new InitialValues();
        iv.printInitialValues();

        new InitialValues().printInitialValues();
    }
}
