package InitializeAndCleanUp.part4;

/**
 * this的作用：2.将当前对象传递给其他方法。将自身传递给外部方法。
 * Created by chenyang on 2017/1/2.
 */

class Person{
    public void eat(Apple apple)
    {
        Apple appled=apple.getPeeled();
        System.out.println("Yummy");
    }
}

class Peeler{
    static Apple peel(Apple apple){
        return apple;
    }

}

class Apple{
    Apple getPeeled(){
        return Peeler.peel(this);
    }
}

public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}
