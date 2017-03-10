package Lock;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by chenyang on 2017/3/1.
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException{
        SynchronousQueue<String> queue=new SynchronousQueue<String>();
        queue.take();
    }
}
