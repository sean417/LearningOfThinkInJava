package io.part9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by chenyang on 16/10/19.
 */
public class OSExecute {
    public static void command(String command){
        boolean err=false;
        try {
            Process process=
                    new ProcessBuilder(command.split(" ")).start();
            BufferedReader results=new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String s;
            while((s=results.readLine())!=null){
                System.out.println(s);
            }
            BufferedReader errors=new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            while((s=errors.readLine())!=null){
                System.out.println(s);
                err=true;
            }
        }catch (Exception e){
            if(!command.startsWith("CMD /C")){
                command("CMD /C"+command);
            }else {
                throw new RuntimeException(e);
            }

        }
        if (err){
            throw new OSExecuteExeception("Errors executing "+command);
        }
    }
}
