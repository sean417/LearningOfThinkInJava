package InitializeAndCleanUp.part8;

/**
 *
 * 1.编译器用自动包装机制匹配重载方法
 * 2.但是对于f(),编译器就不知道用那个了。
 * Created by chenyang on 2017/1/19.
 */
public class OverloadingVarargs {
    static void f(Character... args){
        System.out.println("first");
        for(Character c:args){
            System.out.println(" "+c);
        }
        System.out.println();
    }

    static void f(Integer... args){
        System.out.println("second");
        for(Integer i:args){
            System.out.println(" "+i);
        }
        System.out.println();
    }

    static void f(Long... args){
        System.out.println("third");
    }


    public static void main(String[] args) {
        f('a','b','c');
        f(1);
        f(2,1);
        f(0);
        f(0L);
        //f();ambiguous
    }
}
