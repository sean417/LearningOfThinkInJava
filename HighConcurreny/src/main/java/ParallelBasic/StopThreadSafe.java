package ParallelBasic;

/**
 * 安全的终止线程
 * 在ChangeObjectThread类中定义了一个标记变量stopme,用于指示线程是否退出
 * while(true)中会判断这个标记从而break的方式终止线程。
 */
public class StopThreadSafe {
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
        volatile boolean stopme=false;

        public void stopMe(){
            stopme=true;
        }

        @Override
        public void run() {
            while (true){
                if(stopme){
                    System.out.println("exit by stop me");
                }
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
            ChangeObjectThread t=new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stopMe();
        }
    }
}
