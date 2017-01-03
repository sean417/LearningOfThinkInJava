package InitializeAndCleanUp.part1;

/**
 * Created by chenyang on 2017/1/1.
 */
class Rock2 {
    Rock2(int i){
        System.out.println("Rock "+i);
    }
}

public class SimpleConstructor2{
    public static void main(String[] args) {
        for(int i=0;i<8;i++){
            new Rock2(i);
        }
    }
}
