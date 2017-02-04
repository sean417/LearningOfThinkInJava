package ClassLoader;

/**
 * Created by chenyang on 2017/2/4.
 */
public class Parent {
    static {
        System.out.println("Parent init");
    }
    public static int v=100;
}
