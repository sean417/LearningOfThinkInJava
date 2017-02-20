package GoIntoConcurrency;

/**
 *
 * 一.原子性：指一个操作是不可中断的。即使在多线程一起执行的时候，一个操作一旦开始就不会被干扰。
 * int型是原子操作类型。但在32位系统来说，long型数据的读写不是原子类型的。因为long型长度是64位。
 * 64位和32位是系统的寻址长度。
 *
 * Created by chenyang on 2017/2/19.
 */
public class MultiThreadLong {
    public static long t=0;
    public static class ChangeT implements Runnable{
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        public void run() {
            while (true){
                MultiThreadLong.t=to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{
        public void run() {
            while (true){
                long tmp=MultiThreadLong.t;
                if(tmp!=111L&&tmp!=-999L&&tmp!=333L&&tmp!=-444L){
                    System.out.println(tmp);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444)).start();
        new Thread(new ReadT()).start();
    }
}
