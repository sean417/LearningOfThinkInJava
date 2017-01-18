package InitializeAndCleanUp.part8;

import static net.mindview.util.Print.print;

/**
 * java数组初始化时就确定了长度，要是超过数组边界，java会报运行时错误。
 * Created by chenyang on 2017/1/18.
 */
public class ArraysOfPrimitives {
    public static void main(String[] args) {
        int[] a1={1,2,3,4,5};
        int[] a2;
        a2=a1;
        for(int i=0;i<a2.length;i++){
            a2[i]=a2[i]+1;
        }
        a1[10]=3;
        for(int i=0;i<a1.length;i++){
            print("a1["+i+"]="+a1[i]);
        }
    }
}
