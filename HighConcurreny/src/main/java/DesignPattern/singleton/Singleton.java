package DesignPattern.singleton;

/**Singleton实现方法一：直接用static,保证线程安全，且被实例化一次。
 * 缺点：无法懒加载，因为我调用Singleton.STATUS时，实例也会被创建。
 *
 * Created by chenyang on 2017/3/24.
 */
public class Singleton {
    public static int STATUS=1;
    private Singleton(){
        System.out.println("Singleton is create");
    }

    private static Singleton instance=new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.STATUS);
    }
}
