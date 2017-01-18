package InitializeAndCleanUp.part8;

import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2017/1/18.
 */
public class ArrayNew {
    public static void main(String[] args) {
        int[] a;
        Random rand=new Random(47);
        a=new int[rand.nextInt(20)];
        print("length of a="+a.length);
        print(Arrays.toString(a));
    }
}
