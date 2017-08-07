package concurrentDebugAndJdk8.StampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * 更快的原子类：StampedLock是提供了一个一个乐观读写锁。
 * 1.通过使用tryOptimisticRead()或tryOptimisticWrite()来实现读和写线程可以同时
 * 操作数据，同时用stamp来维护版本，使数据不会发生不一致。
 * 2.同时StampedLock也有悲观的读写锁，读写互斥，写写互斥。
 * Created by chenyang3@01zhuanche.com on 2017/7/31.
 */
public class StampedLockTest {
    private double x,y;
    private  final StampedLock sl=new StampedLock();

    void move(double deltaX,double deltaY){
        long stamp=sl.writeLock();
        try{
            x+=deltaX;
            y+=deltaY;
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){
    long stamp=sl.tryOptimisticRead();
    double currentX=x,currentY=y;
    if(!sl.validate(stamp)){
        stamp=sl.readLock();
        try{
            currentX=x;
            currentY=y;
        }finally {
            sl.unlockRead(stamp);
        }
    }
    return Math.sqrt(currentX*currentX+currentY*currentY);
    }

    public static void main(String[] args) {
        StampedLockTest test=new StampedLockTest();
        test.distanceFromOrigin();
    }
}
