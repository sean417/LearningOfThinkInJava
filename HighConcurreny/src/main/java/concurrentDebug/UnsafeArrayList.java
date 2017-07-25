package concurrentDebug;

import java.util.ArrayList;

/**
 * Created by chenyang3@01zhuanche.com on 2017/7/21.
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
