package basic.AOP;
import java.io.PrintStream;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }
    //探险之前调用
    public void singBeforeQuest(){
        stream.println("Fa la la,the knight is so brave!");
    }

    public void singAfterQuest(){
        stream.println("Tee hee hee,the brave knight did embark on a quest!");
    }


}
