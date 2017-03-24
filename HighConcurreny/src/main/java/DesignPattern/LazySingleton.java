package DesignPattern;

/**Singleton实现二：
 * 在第一次被调用时创建对象。
 * 采用synchronized防止多线程争抢资源。
 * synchronized这样会很消耗资源。
 * Created by chenyang on 2017/3/24.
 */
public class LazySingleton {
    private LazySingleton(){
        System.out.println("LazySingletom is create");
    }
    private static LazySingleton instance=null;

    public static synchronized LazySingleton getInstance(){
        if(instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }
}
