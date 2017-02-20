package GoIntoConcurrency;

/**
 *
 * 一.原子性：
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
