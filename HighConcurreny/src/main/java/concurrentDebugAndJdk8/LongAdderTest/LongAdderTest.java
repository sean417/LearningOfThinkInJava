package concurrentDebugAndJdk8.LongAdderTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 *更快的原子类LongAdder：
 *采用分离热点数据的方法提升并发能力。
 * 1.热点数据被分离成多个单元的cell,每个cell独自维护内部的值，当前对象的实际值由所有的cell累计合成，这样
 * 提升了并行度。
 * 2.LongAdder并不会一开始就用数组进行处理，而是将所有数据都先记录在一个称为base的变量中。
 * 如果多线程修改base时没有发生冲突，那么没有必要扩展cell数组。
 * 3.但是一旦base修改发生冲突（CAS方式），就会初始化cell数组，使用新的策略。
 * 4.如果使用Cell数组更新后，发现某一个cell上更新依然发生冲突，那么系统会尝试创建新的cell,
 * 或者将cell的数量加倍，以减少冲突的可能。
 * Created by chenyang3@01zhuanche.com on 2017/7/30.
 */
public class LongAdderTest {
    protected static final int MAX_THREADS=3;//线程数
    protected static final int TASK_COUNT=10;//任务数
    protected static final int TARGET_COUNT=50000000;//目标总数

    protected AtomicLong acount=new AtomicLong(0L);
    protected LongAdder lacount=new LongAdder();
    private long count=0;

    static CountDownLatch cdlsync=new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic=new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr=new CountDownLatch(TASK_COUNT);

    protected  synchronized long inc(){
        return ++count;
    }

    protected synchronized long getCount(){
        return count;
    }
    public static void main(String[] args) {

    }
}
