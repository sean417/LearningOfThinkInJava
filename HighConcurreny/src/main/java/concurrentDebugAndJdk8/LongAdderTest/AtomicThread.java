package concurrentDebugAndJdk8.LongAdderTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static concurrentDebugAndJdk8.LongAdderTest.LongAdderTest.*;
import static concurrentDebugAndJdk8.LongAdderTest.LongAdderTest.TASK_COUNT;

/**原子类的性能测试。
 * Created by chenyang3@01zhuanche.com on 2017/8/1.
 */
public class AtomicThread implements Runnable{

    protected String name;
    protected long starttime;
    LongAdderTest out;

    public AtomicThread(LongAdderTest out,long starttime) {
        this.starttime = starttime;
        this.out = out;
    }

    @Override
    public void run() {
        long v=out.acount.get();
        while (v<TARGET_COUNT){
            v=out.acount.incrementAndGet();
        }
        long endtime=System.currentTimeMillis();
        System.out.println("atomicThread spend:"+(endtime-starttime)+"ms"+" v="+v);
        cdlatomic.countDown();
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exe= Executors.newFixedThreadPool(MAX_THREADS);
        long starttime=System.currentTimeMillis();
        AtomicThread atomic=new AtomicThread(new LongAdderTest(),starttime);
        for(int i=0;i<TASK_COUNT;i++){
            exe.submit(atomic);
        }
        cdlatomic.await();
        exe.shutdown();
    }
}
