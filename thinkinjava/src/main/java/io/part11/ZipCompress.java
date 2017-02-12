package io.part11;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

import static net.mindview.util.Print.print;

/**
 * Created by chenyang on 2016/10/24.
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException{
        FileOutputStream f=new FileOutputStream("./src/main/java/io/source/data.zip");
        CheckedOutputStream csum=
                new CheckedOutputStream(f,new Adler32());
        ZipOutputStream zos=new ZipOutputStream(csum);
        BufferedOutputStream out=new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        String[] argss=new String[]{"./src/main/java/io/source/data1.txt","./src/main/java/io/source/data2.txt"};
        for(String arg:argss){
            print("Writing file "+arg);
            BufferedReader in=
                    new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c=in.read())!=-1){
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();

        print("Checksum: "+csum.getChecksum().getValue());

        print("Reading file");

        FileInputStream fi=new FileInputStream("./src/main/java/io/source/data.zip");
        CheckedInputStream csumi=new CheckedInputStream(fi,new Adler32());
        ZipInputStream in2=new ZipInputStream(csumi);
        BufferedInputStream bis=new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze=in2.getNextEntry())!=null){
            print("Reading file "+ze);

            int x;
            while ((x=bis.read())!=-1){
                System.out.write(x);
            }
        }
        if(argss.length==1){
            print("Checksum: "+csumi.getChecksum().getValue());
        }
        bis.close();


        ZipFile zf=new ZipFile("./src/main/java/io/source/data.zip");
        Enumeration e=zf.entries();
        while (e.hasMoreElements()){
            ZipEntry ze2=(ZipEntry)e.nextElement();
            print("File: "+ze2);
        }
    }
}
