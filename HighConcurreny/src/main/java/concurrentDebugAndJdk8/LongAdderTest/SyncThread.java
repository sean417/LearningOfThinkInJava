package concurrentDebugAndJdk8.LongAdderTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static concurrentDebugAndJdk8.LongAdderTest.LongAdderTest.*;

/**同步锁的性能测试。
 * Created by chenyang on 2017/8/1.
 */
public class SyncThread implements Runnable{
    protected String name;
    protected long starttime;
    LongAdderTest out;
    public SyncThread(LongAdderTest o,long starttime){
        out=o;
        this.starttime=starttime;
    }

    @Override
    public void run() {
        long v=out.getCount();
        while (v<TARGET_COUNT){
            v=out.inc();
        }
        long endtime=System.currentTimeMillis();
        System.out.println("SyncThread spend:"+(endtime-starttime)+"ms"+" v="+v);
        cdlsync.countDown();
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exe= Executors.newFixedThreadPool(MAX_THREADS);
        long starttime=System.currentTimeMillis();
        SyncThread sync=new SyncThread(new LongAdderTest(),starttime);
        for(int i=0;i<TASK_COUNT;i++){
            exe.submit(sync);
        }

        cdlsync.await();
        exe.shutdown();
    }
}
