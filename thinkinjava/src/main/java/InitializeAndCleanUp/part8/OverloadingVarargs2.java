package InitializeAndCleanUp.part8;

/**
 * Created by chenyang on 2017/1/19.
 */
public class OverloadingVarargs2 {
    static void f(float i,Character... args){
        System.out.println("first");
    }
    static void f(Character... args){
        System.out.println("second");
    }

    public static void main(String[] args) {
        f(1,'a');
        //f('a','b');ambiguous.
    }
}
