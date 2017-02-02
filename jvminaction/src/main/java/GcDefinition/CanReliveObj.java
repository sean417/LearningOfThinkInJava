package GcDefinition;

/**
 * 对象的复活：
 * 1.System.gc()之前会执行对象的finalize()方法。
 * 2.对象的finalize()方法让obj又复活了。
 * 3.但是一个对象的finalize()方法只能执行一次，所以第二次不能复活。
 * 4.System.gc()只是开始回收，调用了finalize()后才是真正意义的回收。
 *
 * 如果没有"obj=null;//第二次，不可复活  "这个语句，obj就不可能被回收了呢？不见得！！！
 * 经验：
 * 1.避免使用finalize().
 * 2.优先级低，何时调用不确定。（什么时候gc,程序无法确定，系统决定）
 * 3.可以用try-catch-finally来替代它。
 * Created by chenyang on 2017/2/2.
 */
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj=this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException{
        obj=new CanReliveObj();
        obj=null;//引用清空，可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj 是 null");
        }else {
            System.out.println("obj可用");
        }
        System.out.println("第2次gc");
        obj=null;//不可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj是null");
        }else {
            System.out.println("obj可用");
        }
    }
}
