package jvmbasicstructure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by chenyang on 2017/1/30.
 * -Xmx20m -Xms5m
 * -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError
 */
public class DumpOOM {

    public static void main(String[] args) {
        List<byte[]> list=new ArrayList<byte[]>();

        for(int i=0;i<25;i++){
            list.add(new byte[1*1024*1024]);
        }


    }
}
