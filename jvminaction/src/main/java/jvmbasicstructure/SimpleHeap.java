package jvmbasicstructure;

/**
 *
 * 通过一个例子说明java堆，java栈和方法区之间的关系。
 * SimpleHeap实例本身分配在堆中。描述SimpleHeap类的信息存放在方法区中。
 * main方法中的两个局部变量s1和s2存放在java栈中，并指向堆中的两个实例。
 *
 * Created by chenyang on 2017/1/31.
 */
public class SimpleHeap {

    private int id;
    public SimpleHeap(int id){
        this.id=id;
    }
    public void show(){
        System.out.println("My ID is "+id);
    }

    public static void main(String[] args) {
        SimpleHeap s1=new SimpleHeap(1);
        SimpleHeap s2=new SimpleHeap(2);
        s1.show();
        s2.show();
    }
}
