package io.part1;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by chenyang on 16/10/12.
 */
public class DirList {
    public static void main(String[] args) {
        File path=new File(".");
        String[] list;
        list=path.list(new DirFilter(".*java"));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem:list){
            System.out.println(dirItem);
        }
    }
}

class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter(String regex) {
        pattern=Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
