package InitializeAndCleanUp.part9;

/**
 * 在你创建enum时，编译器会自动添加一些有用特性：
 * 1.创建toString();
 * 2.创建ordinal()，用来表示某个emun常量声明的顺序。
 * Created by chenyang on 2017/1/19.
 */
public class EnumOrder {
    public static void main(String[] args) {
        for(Spiciness s:Spiciness.values()){
            System.out.println(s+",ordinal "+s.ordinal());
        }
    }
}
