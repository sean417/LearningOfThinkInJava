package ClassLoader;

/**
 * Created by chenyang on 2017/2/4.
 */
public class FinalFieldClass {
    public static final String constString="CONST";
    static {
        System.out.println("FinalFieldClass init");
    }
}
