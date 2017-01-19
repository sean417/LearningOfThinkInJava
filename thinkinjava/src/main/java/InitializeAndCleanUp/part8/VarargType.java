package InitializeAndCleanUp.part8;

import net.mindview.util.CountingGenerator;

/**
 * getClass()方法属于Object的一部分，它将产生对象的类。
 * 输出结果中：
 * "["表示后面紧随的类型的数组，I表示int
 * Created by chenyang on 2017/1/19.
 */
public class VarargType {
    static void f(Character... args){
        System.out.println(args.getClass());
        System.out.println(" length "+args.length);
    }
    static void g(int... args){
        System.out.println(args.getClass());
        System.out.println(" length "+args.length);
    }

    public static void main(String[] args) {
        f('a');
        f();
        g(1);
        g();
        System.out.println("int[]:"+new int[0].getClass());
    }
}
