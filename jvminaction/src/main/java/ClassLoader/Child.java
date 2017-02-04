package ClassLoader;

/**
 * Created by chenyang on 2017/2/4.
 */
public class Child extends Parent {
    static {
        System.out.println("Child init");
    }
}
