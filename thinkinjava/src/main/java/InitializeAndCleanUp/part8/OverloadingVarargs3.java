package InitializeAndCleanUp.part8;

/**
 * 解决ambiguous
 * Created by chenyang on 2017/1/19.
 */
public class OverloadingVarargs3 {
    static void f(float i,Character... args){
        System.out.println("first");
    }
    static void f(char c,Character... args){
        System.out.println("second");
    }

    public static void main(String[] args) {
        f(1,'a');
        f('a','b');
    }
}
