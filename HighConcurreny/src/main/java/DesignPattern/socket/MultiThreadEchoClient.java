package DesignPattern.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by chenyang on 2017/3/26.
 */
public class MultiThreadEchoClient {

    public static void main(String[] args) {
        Socket client=null;
        PrintWriter writer=null;
        BufferedReader reader=null;
        try {
            client=new Socket();
            client.connect(new InetSocketAddress("localhost",8000));
            writer=new PrintWriter(client.getOutputStream(),true);
            writer.println("hello"+System.currentTimeMillis());
            writer.flush();
            reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(System.currentTimeMillis()+"from server:"+reader.readLine());
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer!=null){
                writer.close();
            }
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
            if(client!=null){
                try {
                    client.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
