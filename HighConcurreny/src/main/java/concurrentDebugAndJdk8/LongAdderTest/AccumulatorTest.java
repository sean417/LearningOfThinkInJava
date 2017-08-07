package concurrentDebugAndJdk8.LongAdderTest;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * 对于LongAdder来说，它只是每次对给定的整数执行一次加法，
 * 而LongAccumulator则可以实现任意函数操作。
 *
 * 下面例子是，100个线程调用accumulator.accumulate(value);
 * 用Long::max每次判断哪个大，把大的留下，初始值是Long.MIN_VALUE。
 * Created by chenyang3@01zhuanche.com on 2017/8/7.
 */
public class AccumulatorTest {

    public static void main(String[] args) throws Exception{
        LongAccumulator accumulator=new LongAccumulator(Long::max,Long.MIN_VALUE);
        Thread[] ts=new Thread[100];

        for(int i=0;i<100;i++){
            ts[i]=new Thread(()->{
                Random random=new Random();
                long value=random.nextLong();
                accumulator.accumulate(value);
            });
            ts[i].start();
        }

        for(int i=0;i<100;i++){
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}
