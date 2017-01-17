package InitializeAndCleanUp.part6;

/**
 * 这个方法可以带参数，但参数必须是已经被初始化的。
 * Created by chenyang on 2017/1/17.
 */
public class MethodInit2 {
    int i=f();
    int j=g(i);
    int f(){return 11;}
    int g(int n){return n*10;}


}
