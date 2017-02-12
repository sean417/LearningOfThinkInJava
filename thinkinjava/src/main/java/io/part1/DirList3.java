package io.part1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by chenyang on 16/10/13.
 */
public class DirList3 {
    public static void main(String[] args) {
        File path=new File("../javathinkin/src/main/java/io/part1");
        String[] list;
        list=path.list(new FilenameFilter() {
            private Pattern pattern=Pattern.compile(".*java");
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });

        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem:list){
            System.out.println(dirItem);
        }
    }
}
