package InitializeAndCleanUp.part8;

import net.mindview.util.CountingGenerator;

import java.util.Arrays;

/**
 * Created by chenyang on 2017/1/19.
 */
public class ArrayInit {
    public static void main(String[] args) {
        Integer[] a={
                new Integer(1),
                new Integer(2),
                3
        };
        Integer[] b=new Integer[]{
                new Integer(1),
                new Integer(2),
                3,
        };
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

    }
}
