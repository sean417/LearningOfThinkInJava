package concurrentDebugAndJdk8;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁的检查：
 * 相互等待对方释放资源，造成死锁
 * 检查死锁的方式：
 * 1.jps：找到进程号
 * 2.jstack pid: 能够看到死锁的线程，以及它在等待哪个资源。
 * 3.jstack -l pid:-l显示这个线程拥有哪个锁资源。
 * Created by chenyang on 2017/7/28.
 */
public class DeadLockCar extends Thread {
    protected Object myDirect;
    static ReentrantLock south=new ReentrantLock();
    static ReentrantLock north=new ReentrantLock();
    static ReentrantLock west=new ReentrantLock();
    static ReentrantLock east=new ReentrantLock();

    public DeadLockCar(Object obj){
        this.myDirect=obj;
        if(myDirect==south){
            this.setName("south");
        }
        if(myDirect==north){
            this.setName("north");
        }
        if(myDirect==west){
            this.setName("west");
        }
        if(myDirect==east){
            this.setName("east");
        }
    }

    @Override
    public void run() {
         if(myDirect==south) {
             try {
                 west.lockInterruptibly();
                 try {
                     Thread.sleep(500);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 south.lockInterruptibly();
                 System.out.println("car to south has passed");
             }catch (InterruptedException e1){
                 System.out.println("car to south is killed");
             }finally {
                 if(west.isHeldByCurrentThread()){
                     west.unlock();
                 }
                 if(south.isHeldByCurrentThread()){
                     south.unlock();
                 }
             }
         }

        if(myDirect==north) {
            try {
                east.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                north.lockInterruptibly();
                System.out.println("car to north has passed");
            }catch (InterruptedException e1){
                System.out.println("car to north is killed");
            }finally {
                if(north.isHeldByCurrentThread()){
                    north.unlock();
                }
                if(east.isHeldByCurrentThread()){
                    east.unlock();
                }
            }
        }

        if(myDirect==west) {
            try {
                north.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                west.lockInterruptibly();
                System.out.println("car to west has passed");
            }catch (InterruptedException e1){
                System.out.println("car to west is killed");
            }finally {
                if(north.isHeldByCurrentThread()){
                    north.unlock();
                }
                if(west.isHeldByCurrentThread()){
                    west.unlock();
                }
            }
        }

        if(myDirect==east) {
            try {
                south.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                east.lockInterruptibly();
                System.out.println("car to east has passed");
            }catch (InterruptedException e1){
                System.out.println("car to east is killed");
            }finally {
                if(south.isHeldByCurrentThread()){
                    south.unlock();
                }
                if(east.isHeldByCurrentThread()){
                    east.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        DeadLockCar car2south=new DeadLockCar(south);
        DeadLockCar car2north=new DeadLockCar(north);
        DeadLockCar car2west=new DeadLockCar(west);
        DeadLockCar car2east=new DeadLockCar(east);

        car2south.start();
        car2north.start();
        car2west.start();
        car2east.start();

        Thread.sleep(1000);
    }
}
