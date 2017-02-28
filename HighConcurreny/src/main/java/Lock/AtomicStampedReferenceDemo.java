package Lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *  AtomicStampedReference解决了数据不变，但程序运行过程中数据变了的情况。
 *  它使用戳保证数据没有被修改。
 * Created by chenyang on 2017/2/27.
 */
public class AtomicStampedReferenceDemo {
        public static void main(String[] args) throws InterruptedException{
            //设置账户初始值小于20，显然这是个需要被充值的账户。
            final AtomicStampedReference<Integer> money=new AtomicStampedReference<Integer>(19,0);
            //模拟3个线程同时更新后台数据库,为用户充值，（如果第一次充值20成功后，用户消费了20元，别的线程认为低于20没冲过值，又会充值一次）
            for(int i=0;i<3;i++){
                final int timestamp=money.getStamp();
               new Thread(){
                   @Override
                   public void run() {

                           while (true) {
                               Integer m = money.getReference();
                               if (m < 20) {
                                   if (money.compareAndSet(m, m + 20,timestamp,timestamp+1)) {
                                       System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + "元");
                                       break;
                                   } else {
                                       System.out.println("余额大于20元，无需充值");
                                       break;
                                   }
                               }
                           }
                       }

               }.start();
            }
            //用户消费线程。
            new Thread(){
                @Override
                public void run() {
                    for(int i=0;i<100;i++){
                        while (true){
                            int timestamp=money.getStamp();
                            Integer m=money.getReference();
                            if(m>10){
                                System.out.println("大于10元");
                                if(money.compareAndSet(m,m-10,timestamp,timestamp+1)){
                                    System.out.println("成功消费10元，余额："+money.getReference());
                                    break;
                                }
                            }else {
                                System.out.println("没有足够的金额");
                                break;
                            }
                        }
                        try {
                            Thread.sleep(100);
                        }catch (InterruptedException e){

                        }
                    }
                }
            }.start();
    }
}
