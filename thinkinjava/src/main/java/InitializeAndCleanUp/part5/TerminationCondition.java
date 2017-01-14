package InitializeAndCleanUp.part5;

import net.mindview.util.CountingGenerator;

/**
 * 一.jvm负责释放java内存，指的是jvm只知道释放那些由new分配的内存。
 * 不会释放非new出来的特使内存。如java类库中调用本地方法（如用C的malloc()函数系列来分配存储空间)。
 * 二.finalize用处之一是：对象终结条件的验证。
 * 三.这个例子的终结条件是，所有的Book对象在被当作垃圾回收钱都应该被签入（check in）.
 * 但在main()方法中，有一本书未被签入。要是没有finalize()来验证终结条件，将很难发现这个缺陷。
 * 四.System.gc()用于强制进行终结动作。
 * Created by chenyang on 2017/1/14.
 */


class Book{
    boolean checkedOut=false;
    Book(Boolean checkOut){
        checkedOut=checkOut;
    }
    void checkIn(){
        checkedOut=false;
    }

    @Override
    protected void finalize() throws Throwable {
        if(checkedOut){
            System.out.println("Error:checked out");
        }
    }
}
public class TerminationCondition {
    public static void main(String[] args) {
        Book novel=new Book(true);
        //清理
        novel.checkIn();


        //方法1：循环创建未清理的对象
//        for(int i=0;true;) {
//            new Book(true);
//            new Book(true);
//
//            new Book(true);
//
//            new Book(true);
//            new Book(true);
//            new Book(true);
//            new Book(true);
//        }


//方法二：使用System.gc()用于强制进行终结对象动作
        new Book(true);
        System.gc();
    }
}
