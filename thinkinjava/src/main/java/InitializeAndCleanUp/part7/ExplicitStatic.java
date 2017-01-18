package InitializeAndCleanUp.part7;

import static net.mindview.util.Print.print;

/**无论把标为1的行和2的行只有执行一个，Cups的静态初始化
 * 就会得到进行。都注释掉就不会进行了。但Cups的静态初始化
 * 只会执行一次。
 * Created by chenyang on 2017/1/18.
 */
class Cup{
    Cup(int marker){
        print("Cup("+marker+")");
    }
    void f(int marker){
        print("f("+marker+")");
    }
}

class Cups{
    static Cup cup1;
    static Cup cup2;
    static {
        cup1=new Cup(1);
        cup2=new Cup(2);
    }
    Cups(){
        print("Cup()");
    }
}
public class ExplicitStatic {
    public static void main(String[] args) {
        print("Inside main()");
        Cups.cup1.f(99);
    }
    static Cups cups1=new Cups();//1
    static Cups cups2=new Cups();//2

}
