package InitializeAndCleanUp.part7;

import static net.mindview.util.Print.print;

/**初始化顺序：
 * 1.变量定义的鲜花顺序决定了初始化的顺序。
 * 2.即使变量定义散布于方法定义之间，他们仍然会在任何方法（包括构造器）被调用之前
 * Created by chenyang on 2017/1/17.
 */

class Window{
    Window(int marker){
        print("Window("+marker+")");
    }
}

class House{
    Window w1=new Window(1);
    House(){
        print("House()");
        w3=new Window(33);
    }
    Window w2=new Window(2);
    void f(){print("f()");}
    Window w3=new Window(3);

}

public class OrderOfInitialization {
    public static void main(String[] args) {
        House h=new House();
        h.f();
    }
}
