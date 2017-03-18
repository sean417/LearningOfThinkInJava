package jdk1;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**CyclicBarrier:循环栅栏，让一个线程等待所有线程都执行完了再执行，而且可以循环等待。
 * 第一个参数为等待的个数，第二个参数为等待结束后本线程要执行的任务。
 * 使用cyclic.await();挂起一个线程，且计数器加一。
 *
 * Created by chenyang on 2017/3/12.
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclic;

        public Soldier(CyclicBarrier cyclic,String soldier) {
            this.soldier = soldier;
            this.cyclic = cyclic;
        }

        public void run() {
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
        }

        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(soldier+":任务完成");
        }

    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        public void run() {
            if(flag){
                System.out.println("司令:[士兵"+N+"个，任务完成！]");
            }else {
                System.out.println("司令:[士兵"+N+"个，集合完毕！]");
                flag=true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int N=10;
        Thread[] allSoldier=new Thread[N];
        boolean flag=false;
        CyclicBarrier cyclic=new CyclicBarrier(N,new BarrierRun(flag,N));

        System.out.println("集合队伍！");
        for(int i=0;i<N;++i){
            System.out.println("士兵 "+i+"报道！");
            allSoldier[i]=new Thread(new Soldier(cyclic,"士兵"+i));
            allSoldier[i].start();
//            if(i==5) {这个中断会引起一个interrupt()和n个BrokenBarrierException异常。意思是栅栏破坏掉了，
// 线程永远无法完成栅栏
//                allSoldier[1].interrupt();
//            }
        }
    }
}
