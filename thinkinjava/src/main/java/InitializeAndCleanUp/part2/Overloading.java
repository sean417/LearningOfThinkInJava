package InitializeAndCleanUp.part2;

import static net.mindview.util.Print.print;

/**
 *
 * 参数列表不同的重载方法。
 * Created by chenyang on 2017/1/1.
 */
class Tree{
    int height;
    Tree(){
        print("Planting a seeding");
        height=0;
    }

    Tree(int initialHeight){
        height=initialHeight;
        print("Creating new Tree that is "+
        height+" feet tall");
    }

    void info(){
        print("Tree is "+height+" feet tall");
    }

    void info(String s){
        print(s+": Tree is "+height+" feet tall");
    }
}


public class Overloading {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            Tree t=new Tree(i);
            t.info();
            t.info("overloaded method");
        }
        new Tree();
    }
}
