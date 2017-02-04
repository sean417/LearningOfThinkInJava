package ClassLoader;

/**
 * FinalFieldClass没有初始化。
 * 调用类的常量final修饰的，不会初始化。
 * Created by chenyang on 2017/2/4.
 */
public class UserFinalField {
    public static void main(String[] args) {
        System.out.println(FinalFieldClass.constString);
    }
}
