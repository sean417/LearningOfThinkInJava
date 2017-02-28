package Lock;

import java.util.concurrent.atomic.AtomicReference;

/**使用引用类型AtomicReference<T>的原子操作。
 *当用户账户小于20元时，给用户冲20元，但一个用户只能充值一次。
 *
 * Created by chenyang on 2017/2/28.
 */
public class AtomicReferenceDemo {
   public static final AtomicReference<String> atomicStr=new AtomicReference<String>("abc");

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            final int num=i;
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.abs((int)(Math.random()*100)));
                    }catch (InterruptedException e){
                        e.printStackTrace();;
                    }
                    if(atomicStr.compareAndSet("abc","def")) {
                        System.out.println("Thread"+Thread.currentThread().getId()+" changed");
                    }
                    else {
                        System.out.println("Thread"+Thread.currentThread().getId()+" fail");
                    }
                }
            }.start();
        }
    }

}
