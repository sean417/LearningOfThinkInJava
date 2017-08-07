package concurrentDebugAndJdk8.LongAdderTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static concurrentDebugAndJdk8.LongAdderTest.LongAdderTest.*;

/**LongAdder的性能测试。
 * Created by chenyang3@01zhuanche.com on 2017/8/1.
 */
public class LongAddrThread implements Runnable{

    protected String name;
    protected long starttime;
    LongAdderTest out;

    public LongAddrThread(LongAdderTest out, long starttime) {
        this.starttime = starttime;
        this.out = out;
    }

    @Override
    public void run() {
        long v=out.lacount.sum();
        while (v<TARGET_COUNT){
            out.lacount.increment();
            v=out.lacount.sum();
        }
        long endtime=System.currentTimeMillis();
        System.out.println("LongAdder spend:"+(endtime-starttime)+"ms"+" v="+v);
        cdladdr.countDown();
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exe= Executors.newFixedThreadPool(MAX_THREADS);
        long starttime=System.currentTimeMillis();
        LongAddrThread longAddr=new LongAddrThread(new LongAdderTest(),starttime);
        for(int i=0;i<TASK_COUNT;i++){
            exe.submit(longAddr);
        }
        cdladdr.await();
        exe.shutdown();
    }
}
