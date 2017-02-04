package ClassLoader;

/**
 * 主动使用：
 * 1）当创建一个类的实例时，比如使用new关键字，或者通过反射，克隆，反序列化。
 * 2）当调用类的静态方法时，即当用了字节码invokestatic指令。
 * 3）当使用类或接口的静态字段时（final常量除外）,比如使用getstatic,putstatic指令。
 * 4）当使用java.lang.reflect包中的方法反射类的方法时。
 * 5）当初始化子类时，要求先初始化父类。
 * 6）作为启动虚拟机，含有main()方法的类。
 * Created by chenyang on 2017/2/4.
 */
public class InitMain {
    public static void main(String[] args) {
//        Child c=new Child();//主动使用。系统先装载parent类，再装载Child类，初始化子类时，先初始化父类。
        System.out.println(Child.v);//被动使用。子类调用父类的静态变量。父类初始化了，子类没有。
                                    // 可见，在引用一个字段时，只有直接定义该字段的类，才会被初始化。
    }
}
