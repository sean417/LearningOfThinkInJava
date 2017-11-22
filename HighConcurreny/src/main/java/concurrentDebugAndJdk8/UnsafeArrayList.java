package concurrentDebugAndJdk8;

import java.util.ArrayList;

/** intellij调试多线程：
 * 1.如何没有任何设置，并把断点放置在ArrayList的add方法，会看到是主线程在做classload时的调用，
 * 而不是子线程t1,t2对ArrayList的add方法的调用。
 * 2.在debug中，要想看到子线程对ArrayList的add方法的调用，在断点属性上要设置Condition,
 * 如：!Thread.currentThread().getName().equals("main")，这样断点ArrayList的add方法里的断点之后
 * 在子线程真正调用add方法时停下。
 * 3.对于suspend选项：thread是应用线程的断掉，all代表所有线程包括vm的线程都会断掉。
 * Created by chenyang on 2017/7/21.
 */
public class UnsafeArrayList {
    static ArrayList al=new ArrayList();
    static class AddTask implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
            }
            for(int i=0;i<1000000;i++){
                al.add(new Object());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1=new Thread(new AddTask(),"t1");
        Thread t2=new Thread(new AddTask(),"t2");
        t1.start();
        t2.start();
        Thread t3=new Thread(new Runnable(){
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){}
                }
            }
        },"t3");
        t3.start();
    }


}
