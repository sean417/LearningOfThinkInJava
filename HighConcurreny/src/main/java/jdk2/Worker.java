package jdk2;

/**
 * Created by chenyang on 2017/3/18.
 */
public class Worker extends Thread {
    //线程池
    private ThreadPool pool;
    //任务
    private Runnable target;
    private boolean isShutDown=false;
    private boolean isIdle=false;

    public Worker(Runnable target,String name,ThreadPool pool){
        super(name);
        this.pool=pool;
        this.target=target;
    }

    public Runnable getTarget(){
        return target;
    }

    public boolean isIdle(){
        return isIdle;
    }
    public void setTarget(Runnable target){
        this.target=target;
        notifyAll();
    }

    @Override
    public void run() {
        while (!isShutDown){
            isIdle=false;
            if(target!=null){
                //运行任务
                target.run();
            }
            //任务结束了
            isIdle=true;
            try {
                //该任务结束后，不关闭线程，而是放入线程池空闲队列
                pool.repool(this);
                synchronized (this){
                    wait();
                }
            }catch (InterruptedException ie){

            }
        }
    }

    public synchronized void shutDown(){
        isShutDown=true;
        notifyAll();
    }
}
