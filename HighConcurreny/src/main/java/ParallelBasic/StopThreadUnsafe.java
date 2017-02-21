package ParallelBasic;

/**
 * 终止线程stop():立即终止线程：
 * stop()这个方法已经过时，不建议使用，因为太过于暴力的终结线程，会造成
 * 数据不一致。如下面例子，ChangeObjectThread写数据一半时（user的id变化了，但是name没有来得及写），
 * 读线程启动这样就造成数据的不一致
 * Created by chenyang on 2017/2/21.
 */
public class StopThreadUnsafe {
    public static User u=new User();
    public static class User{
        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    int v=(int) (System.currentTimeMillis()/1000);
                    u.setId(v);

                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.fillInStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId()!=Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new ReadObjectThread().start();
        while (true){
            Thread t=new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
