package DesignPattern.jdkFuture;

import java.util.concurrent.Callable;

/**
 * Created by chenyang on 2017/3/26.
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<10;i++){
            sb.append(para);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }
        return sb.toString();
    }
}
