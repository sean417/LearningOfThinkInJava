package InitializeAndCleanUp.part8;

/**
 * 有了可变参数，就不用显示地编写数组语法了。
 * 1.当你指定参数时（独立的元素），编译器填充数组，你获得依然是数组，这就是为什么printArray能用foreach迭代该数组的原因。
 * 2.当参数为数组时，编译器就不用转换为数组了。
 * 3.可以传0个参数。
 * Created by chenyang on 2017/1/19.
 */
public class NewVarArgs {
    static void printArray(Object... args){//可变参数
        for(Object obj:args){
            System.out.println(obj+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        //参数为独立的元素
        printArray(new Integer(47),new Float(3.14),new Double(11.11));
        printArray(47,3.14F,11.11);
        printArray("one","two","three");
        printArray(new A(),new A(),new A());
        printArray((Object[]) new Integer[]{1,2,3,4});//参数为数组
        printArray();
    }
}
