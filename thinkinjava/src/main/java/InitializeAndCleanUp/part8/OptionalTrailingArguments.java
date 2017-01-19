package InitializeAndCleanUp.part8;

/**
 * 可变参数可以是0个参数
 * 当具有可选的尾随参数时，这个特性很有用。
 * Created by chenyang on 2017/1/19.
 */
public class OptionalTrailingArguments {
    static void f(int required,String... trailing)
    {
        System.out.println("required:"+required+" ");
        for(String s:trailing){
            System.out.println(s+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        f(1,"one");
        f(2,"two","three");
        f(0);
    }
}
