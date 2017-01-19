package InitializeAndCleanUp.part8;

/**
 * Created by chenyang on 2017/1/19.
 */
public class DynamicArray {
    public static void main(String[] args) {
        Other.main(new String[]{"fiddle","de","dum"});
    }
}

class Other{
    public static void main(String[] args) {
        for(String s:args){
            System.out.println(s+" ");
        }
    }
}
