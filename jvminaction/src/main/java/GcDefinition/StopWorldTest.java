package GcDefinition;

import java.util.HashMap;

/**
 * Created by chenyang on 2017/2/2.
 */

public class StopWorldTest {

    public static class MyThread extends Thread{
        HashMap<Long,byte[]> map=new HashMap<Long, byte[]>();

        @Override
        public void run() {
            try {
                while (true){
                    if(map.size()*512/1024/1024>=450){
                        System.out.println("============准备清理==========:"+map.size());//大于450M时，清理内存。
                        map.clear();
                        System.out.println("clean map");
                    }
                    for(int i=0;i<1024;i++){
                        map.put(System.nanoTime(),new byte[512]);//消耗内存。
                    }
                    Thread.sleep(1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static class PrintThread extends Thread {

        public static final long starttime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - starttime;
                    System.out.println("time:" + t);
                    Thread.sleep(100);
                }
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        MyThread t=new MyThread();
        PrintThread p=new PrintThread();
        t.start();
        p.start();
    }
}
