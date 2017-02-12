package io.part1;

import java.io.File;

/**
 * Created by chenyang on 16/10/14.
 */
public class MakeDirectories {
    private static void usage(){
        System.err.println("Usage:MakeDirectories path1  ...\n"+
        "Creates each path \n"+
        "Usage:MakeDirectories -d path1 ...\n"+
        "Deletes each path\n"+
        "Usage:MakeDirectories -r path1 path2\n"+
        "Rename from path1 to path2");
        System.exit(1);
    }

    private static void fileData(File f){
        System.out.println("Absolute path:"+f.getAbsolutePath()+
        "\n Can read:"+f.canRead()+
        "\n Can write:"+f.canWrite()+
        "\n getName:"+f.getName()+
        "\n getParent:"+f.getParent()+
        "\n getPath:"+f.getPath()+
        "\n length:"+f.length()+
        "\n lastModified:"+f.lastModified()+
        "\n allFiles:"+f.list()[0].toString());
        for(String i:f.list()){
            System.out.println(i);
        }

        if(f.isFile()){
            System.out.println("It's a file");
        }else if(f.isDirectory()){
            System.out.println("It's a directory");
        }

    }

    public static void main(String[] args) {
            fileData(new File("//Users"));
    }
}
