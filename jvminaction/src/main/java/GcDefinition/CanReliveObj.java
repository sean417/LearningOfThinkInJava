package GcDefinition;

/**
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
        obj=null;//可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj 是 null");
        }
    }
}
