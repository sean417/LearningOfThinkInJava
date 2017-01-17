package InitializeAndCleanUp.part6;

/**
 * g(i)的实参i没有初始化，所以编译错误
 * Created by chenyang on 2017/1/17.
 */
public class MethodInit3 {
    //！int j=g(i);//illigal forword reference
    int f(){return 11;}
    int g(int n){return n*10;}
}
