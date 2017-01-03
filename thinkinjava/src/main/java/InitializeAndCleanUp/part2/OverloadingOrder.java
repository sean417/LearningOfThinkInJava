package InitializeAndCleanUp.part2;

import static net.mindview.util.Print.print;

/**
 * 不建议参数列表不同的重载方法，因为不好维护
 * Created by chenyang on 2017/1/1.
 */
public class OverloadingOrder {

    static void f(String s,int i){
        print("String: "+s+",int: "+i);
    }
    static void f(int i,String s){
        print("int :"+i+",String :"+s);
    }

    public static void main(String[] args) {
        f("String first",11);
        f(99,"Int first");
    }
}
