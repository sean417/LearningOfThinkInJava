package ParallelBasic;

/**
 * 如果ready不被valitale修饰，子线程永远无法知道ready=true。子线程永远循环下去。
 * Created by chenyang on 2017/2/25.
 */
public class NoVisibility {
    private static volatile boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new ReaderThread().start();
        Thread.sleep(1000);
        number=42;
        ready=true;
        Thread.sleep(10000);
    }
}
