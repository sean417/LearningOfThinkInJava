package InitializeAndCleanUp.part8;

/**
 * 1.可变参数列表与自动包装机制可以和谐共处。
 * 2.可以再单一的参数列表中将类型混合一起，而自动
 * 包装机制将有选择的将int参数提升为Integer。
 * Created by chenyang on 2017/1/19.
 */
public class AutoboxingVarargs {

    public static void f(Integer... args){
        for(Integer i:args){
            System.out.println(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        f(new Integer(1),new Integer(2));
        f(4,5,6,7,8,9);
        f(10,new Integer(11),12);
    }
}
