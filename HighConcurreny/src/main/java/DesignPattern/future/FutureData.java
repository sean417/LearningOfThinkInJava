package DesignPattern.future;

/**
 * Created by chenyang on 2017/3/25.
 */
public class FutureData implements Data {
    protected RealData realData=null;
    protected boolean isReady=false;

    public synchronized void setRealData(RealData realdata){
        if(isReady){
            return;
        }
        this.realData=realdata;
        isReady=true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        return realData.result;
    }
}
