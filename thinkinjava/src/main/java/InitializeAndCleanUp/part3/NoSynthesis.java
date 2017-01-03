package InitializeAndCleanUp.part3;

/**
 *
 * 如果你没有提供构造器，编译器会默认构造一个默认构造器。
 * 但是，如果你写了至少一个构造器，编译器将不会自动给你
 * 构造默认构造器。
 * Created by chenyang on 2017/1/1.
 */
public class NoSynthesis {

    public static void main(String[] args) {
        Bird2 bird2=new Bird2(1);
        Bird2 b3=new Bird2(1.0);
       // new Bird2();  没有找到匹配的构造器
    }
}

class Bird2{
    Bird2(int i){}
    Bird2(double d){}
}
