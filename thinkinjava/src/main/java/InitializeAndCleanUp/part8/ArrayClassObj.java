package InitializeAndCleanUp.part8;

import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * Integer[] a=new Integer[rand.nextInt(20)];//这一行只是一个引用数组
 *a[i]=rand.nextInt(500);//autoboxing//直到通过创建新的Integer对象，
 * 并把对象赋值给引用，初始化进程才算结束。
 * Created by chenyang on 2017/1/18.
 */
public class ArrayClassObj {
    public static void main(String[] args) {
        Random rand=new Random(47);
        Integer[] a=new Integer[rand.nextInt(20)];
        print("length of a="+a.length);
        for(int i=0;i<a.length;i++){
            a[i]=rand.nextInt(500);//autoboxing
        }
        print(Arrays.toString(a));
    }
}
