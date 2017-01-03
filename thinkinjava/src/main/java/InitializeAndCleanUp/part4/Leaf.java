package InitializeAndCleanUp.part4;

/**
 * this的用处：1.返回当前对象的引用。
 * Created by chenyang on 2017/1/2.
 */
public class Leaf {
    int i=0;
    Leaf increment(){
        i++;
        return this;
    }
    void print(){
        System.out.println("i="+i);
    }

    public static void main(String[] args) {
        Leaf x=new Leaf();
        x.increment().increment().increment().print();
    }
}
