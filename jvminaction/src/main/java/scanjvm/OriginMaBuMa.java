package scanjvm;

/**
 * 整数在计算机中使用补码表示。
 * 使用位运算查看整数中每一位的实际值。
 * 相对于原码，使用补码作为计算机内的实际存储方式的好处：
 * 1）可以统一数字零的表示。由于零既非正数，又非负数，如果使用原码表示零，把零归入正数和负数得到的原码编码是不同的。
 *    但是使用补码，无论把零归入正数还是负数的结果都一样。
 * 2）使用补码可以简化整数的加减法计算，将减法计算视为加法计算，实现减法和加法的完全统一，实现加法和减法的完全统一。
 * Created by chenyang on 2017/1/31.
 */
public class OriginMaBuMa {
    public static void main(String[] args) {
        int a=-10;
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000>>>i)>>>(31-i);
            System.out.print(t);
        }
    }
}
