package jvmbasicstructure;

/**
 * 局部变量表,有许多槽位，一个槽位可以容纳32位的数据类型。
 *
 * 对于静态方法runStatic的局部变量表:
 * 槽位下标  类型         参数           大小
 * 0        int         int   i       1个字节
 * 1        long        long  l       2个字节
 * 3        float       float f       1个字节
 * 4        reference   Object o      1个字节
 *
 *
 * 对于实例方法runInstance的局部变量表（第一个槽位加载的是this）：
 * 槽位下标  类型         参数           大小
 * 0        reference    this          1个字节
 * 1        int          char c        1个字节
 * 2        int          short s       1个字节
 * 3        int          boolean b     1个字节
 * Created by chenyang on 2017/1/25.
 *
 *
 */
public class StackDemo {
    public static int runStatic(int i,long l,float f,Object o,byte b){
        return 0;
    }
    public int runInstance(char c,short s,boolean b){
        return 0;
    }
}
