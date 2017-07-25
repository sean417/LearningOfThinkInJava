package DesignPattern.future;

import jdk2.ThreadPool;

/**
 * Created by chenyang on 2017/3/25.
 */
public class Client {
    public Data request(final String queryStr){
        final FutureData future=new FutureData();
        new Thread(){
            public void run(){
                RealData realData=new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) {
        Client client=new Client();

        Data data=client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(50);
        }catch (InterruptedException e){

        }
        System.out.println("数据="+data.getResult());
    }

}
