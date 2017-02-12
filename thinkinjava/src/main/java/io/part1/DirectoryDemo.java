package io.part1;

import java.io.File;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 16/10/14.
 */
public class DirectoryDemo {
    public static void main(String[] args) {
        PPrint.pprint(Directory.walk(".").dirs);
        for(File file:Directory.local(".",".*java")){
            print(file);
        }
        print("---------------------------------");
        for(File file:Directory.walk(".",".*java")){
            print(file);
        }


    }
}
