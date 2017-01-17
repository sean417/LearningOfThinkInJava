package InitializeAndCleanUp.part7;

/**
 * 使用构造器进行初始化。
 * 1.无法阻止自动初始化的进行，它将在构造器被调用之前发生
 * 2.如下的例子，i先会被置0,然后变成7；
 * Created by chenyang on 2017/1/17.
 */
public class Counter {
    int i;
    Counter(){i=7;}
}
