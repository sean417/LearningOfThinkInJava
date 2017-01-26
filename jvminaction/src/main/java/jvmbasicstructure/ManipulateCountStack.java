package jvmbasicstructure;

/**
 *
 *
 *
 * 0： iconst_0   //0压栈
 * 1:  istore_2   //弹出int,存放于局部变量2(局部变量2是c)
 * 2:  iload_0    //把局部变量0(局部变量0是a)压栈
 * 3:  iload_1    //把局部变量1(局部变量1是b)压栈
 * 4:  iadd       //弹出2个变量，求和，结果压栈
 * 5:  istore_2   //弹出结果，放于局部变量2
 * 6:  iload_2    //局部变量2压栈
 * 7:  ireturn    //返回
 *          开始之前       iload_0         iload_1          iadd        isstore_2
 *                                        指令之后         指令之后      指令之后
 *
 * 局部变量  0  100            0 100           0 100       0 100          0 100
 *          1  98             1 98            1 98        1 98           1 98
 *          2                 2               2           2              2 198
 *
 *
 *
 * 操作数栈                    100             100          198
 *                                            98
 *
 * Created by chenyang on 2017/1/25.
 */
public class ManipulateCountStack {
    public static int add(int a,int b){
        int c=0;
        c=a+b;
        return c;
    }
}
