package InitializeAndCleanUp.part7;

import static net.mindview.util.Print.print;

/**
 * 类的静态数据的初始化：
 * 1.类的静态数据初始化，只发生在两种情况下：1）创建这个类的对象。2）直接用类引用类的静态数据
 * 2.类的静态数据初始化，只会初始化一次。因为无论创建多少个对象，静态数据只占用一份存储区域。
 * Created by chenyang on 2017/1/17.
 */
class Bowl{
    Bowl(int marker){
        print("Bowl("+marker+")");
    }
    void f1(int marker){
        print("f1("+marker+")");
    }
}

class Table{
    static Bowl bowl1=new Bowl(1);
    Table(){
        print("Table()");
        bowl2.f1(1);
    }
    void f2(int marker){
        print("f2("+marker+")");
    }
    static Bowl bowl2=new Bowl(2);
}

class Cupboard{
    Bowl bowl3=new Bowl(3);
    static Bowl bowl4=new Bowl(4);
    Cupboard(){
        print("Cupboard");
        bowl4.f1(2);
    }
    void f3(int marker){
        print("f3("+marker+")");
    }
    static Bowl bowl5=new Bowl(5);
}

public class StaticInitialization {
    public static void main(String[] args) {
        print("Creating new Cupboard() in main");
        new Cupboard();
        print("Creating new Cupboard() in main");
        new Cupboard();
    }
    static Table table=new Table();
    static Cupboard cupboard=new Cupboard();
}
