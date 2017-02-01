package jvmbasicstructure;

/**
 * -Xss256k:
 * deep of calling=568
   递归调用了568次

 -Xss512k:
 deep of calling=3030
 递归调用了568次

 Exception in thread "main" java.lang.StackOverflowError
 栈溢出，栈的空间满了。
 也可以通过减少参数或局部变量的个数，减少栈空间的占用，达到函数多调用几次的目的。


 3.调用recursion(a, b, c);-Xss256k：
   最大深度716
 4.调用调用recursion（），-Xss256k：
   最大深度1963
   可以看到在相同的栈容量下，局部变量少的函数可以支持更深的函数调用。

 * Created by chenyang on 2017/1/30.
 */
public class TestStackDeep {
    private static int count=0;
    public static void recursion(long a,long b,long c){
        long e=1,f=2,g=3,h=4,i=5,k=6,q=7,x=8,y=9,z=10;
        count++;
        System.out.println(count);
        recursion(a, b, c);
    }

    public static void recursion(){
        count++;
        System.out.println(count);

        recursion();
    }

    public static void main(String[] args) {
        try {
//            recursion(0L,0L,0L);
            recursion();
        }catch (Exception e){
            System.out.println("deep of calling="+count);
            e.fillInStackTrace();
        }
    }
}
