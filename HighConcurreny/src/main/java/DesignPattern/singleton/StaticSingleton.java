package DesignPattern.singleton;

/**
 * Singleton实现三：
 * 使用内部静态类实现延迟加载而且是线程安全的
 * Created by chenyang on 2017/3/24.
 */
public class StaticSingleton {
    public static int STATUS=1;

    private StaticSingleton(){
        System.out.println("StaticSingleton is create");
    }
    private static class SingletonHolder{
        private static StaticSingleton instance=new StaticSingleton();
    }
    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        System.out.println(StaticSingleton.STATUS);
        StaticSingleton.getInstance();
    }
}
