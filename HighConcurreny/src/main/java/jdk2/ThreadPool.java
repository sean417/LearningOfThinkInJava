package jdk2;

import java.util.List;
import java.util.Vector;

/**
 * Created by chenyang on 2017/3/18.
 */
public class ThreadPool {
    private static ThreadPool instance=null;
    //空闲线程队列
    private List<Worker> idleThreads;
    //已有线程总数
    private int threadCounter;

    private boolean isShutDown=false;

    private ThreadPool(){
        this.idleThreads=new Vector(5);
        threadCounter=0;
    }

    public int getCreatedThreadsCount(){
        return threadCounter;
    }

    //取得线程池实例
    public synchronized static ThreadPool getInstance(){
        if(instance==null){
            instance=new ThreadPool();
        }
        return instance;
    }

    //将线程放入池中
    protected synchronized void repool(Worker repoolingThread){
        if(!isShutDown){
            idleThreads.add(repoolingThread);
        }
        else {
            repoolingThread.shutDown();//关闭线程
        }
    }

    //停止池中所有线程
    public synchronized void shutDown(){
        isShutDown=true;
        for(int threadIndex=0;threadIndex<idleThreads.size();threadIndex++){
            Worker idleThread=idleThreads.get(threadIndex);
            idleThread.shutDown();

        }
    }

    //执行任务
    public synchronized void start(Runnable target){
        Worker thread=null;
        //如果有空闲线程，则直接使用
        if(idleThreads.size()>0){
            int lastIndex=idleThreads.size()-1;
            thread=idleThreads.get(lastIndex);
            idleThreads.remove(lastIndex);
            //立即执行这个任务
            thread.setTarget(target);

        }
        else {
            threadCounter++;
            //创建线程
            thread=new Worker(target,"PThread #"+threadCounter,this);
            //启动这个线程
            thread.start();
        }
    }

}
