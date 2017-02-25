package ParallelBasic;

/**i++看字节码执行会有三步：
 * 读取i的值 -> 值+1 -> 将+1后的值写回i中。
 * volatile不能保证复合操作的原子性：
 * i++是复合操作：
 * Created by chenyang on 2017/2/25.
 */
public class VisibilityCanNotAtomic {

    static volatile int i=0;
    public static class PlusTask implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                i++;
            }
        }
    }

    public static void main(String[] args) throws  InterruptedException{
        Thread[] threads=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i=0;i<10;i++){
            threads[i].join();
        }
        System.out.println(i);
    }
}
