package GcAlgorithm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 *
 * 每一个软引用都可以附带一个引用队列，当对象的可达性发生改变（由可达变为不可达），
 * 软引用对象就会进入引用队列。通过这个引用队列，可以跟踪对象的回收情况。
 *
 *[id=1,name=geym]（从软引用获取对象）
 * After GC:
 * [id=1,name=geym]（GC没有清除软引用指向的对象）
 * try to create byte array and GC （创建大数组，耗尽内存）
 * user id 1 is deleted (引用队列探测到对象被删除)
 * null (由于内存紧张，软引用虽然还在但是对象还是被回收了)
 *
 *
 * Created by chenyang on 2017/2/2.
 */
public class SoftRefQ {
    public static class User{
        public User(int id,String name){
            this.id=id;
            this.name=name;
        }
        public int id;
        public String name;
    }

    static ReferenceQueue<User> softQueue=null;
    public static class CheckRefQueue extends Thread{


        @Override
        public void run() {
            while (true){
                if(softQueue!=null){
                    UserSoftReference obj=null;
                    try {
                        obj=(UserSoftReference)softQueue.remove();

                    }catch (InterruptedException e){
                        e.fillInStackTrace();
                    }

                    if(obj!=null){
                        System.out.println("user id"+obj.uid+" is delete");
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User>{
        int uid;
        public UserSoftReference(User referent,ReferenceQueue<? super User> q){
            super(referent,q);
            uid=referent.id;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t=new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u=new User(1,"chenyang");
        softQueue=new ReferenceQueue<User>();
        UserSoftReference userSoftRef=new UserSoftReference(u,softQueue);

        u=null;
        System.out.println(userSoftRef.get());
        System.gc();

        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        System.out.println("try to create byte array and GC");

        byte[] b=new byte[1024*925*7];
        System.gc();
        System.out.println(userSoftRef.get());

        Thread.sleep(1000);
    }
}
