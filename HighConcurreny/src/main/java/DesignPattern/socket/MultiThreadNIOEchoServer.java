package DesignPattern.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenyang on 2017/4/8.
 */
public class MultiThreadNIOEchoServer {


    public static Map<Socket,Long> geym_time_stat=new HashMap<Socket,Long>(10240);
    class EchoClient{
        private LinkedList<ByteBuffer> outq;
        EchoClient(){
            outq=new LinkedList<ByteBuffer>();
        }
        //return the output queue
        public LinkedList<ByteBuffer> getOutputQueue(){
            return outq;
        }
        //enqueue a ByteBuffer on the output queue.
        public void enqueue(ByteBuffer bb){
            outq.addFirst(bb);
        }
    }



    class HandleMsg implements Runnable{
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient=(EchoClient)sk.attachment();
            echoClient.enqueue(bb);

            //we've enqueued data to be written to the client,we must
            //not set interest in OP_WRITE
            sk.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }

    private Selector selector;
    private ExecutorService tp= Executors.newCachedThreadPool();

    /*
      accept a new client and set it up for reading
     */
    private void doAccept(SelectionKey sk){
        ServerSocketChannel server=(ServerSocketChannel)sk.channel();
        SocketChannel clientChannel;
        try {
            //获取客户端的channel
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            //register the channel for reading
            SelectionKey clientKey=clientChannel.register(selector,SelectionKey.OP_READ);
            //Allocate an EchoClient instance and attach it to this selection key.
            EchoClient echoClient=new EchoClient();
            clientKey.attach(echoClient);

            InetAddress clientAddress=clientChannel.socket().getInetAddress();
            System.out.println("Accepted connetion from "+clientAddress.getHostAddress()+".");
        }catch (Exception e){
            System.out.println("Failed to accept new client");
            e.printStackTrace();
        }
    }

    private void doRead(SelectionKey sk){
        SocketChannel channel=(SocketChannel)sk.channel();
        ByteBuffer bb=ByteBuffer.allocate(8192);
        int len;

        try {
            len=channel.read(bb);
            if(len<0){
                disconnect(sk);
                return;
            }
        }catch (Exception e){
            System.out.println("Fail to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg(sk,bb));
    }

    private void doWrite(SelectionKey sk){
        SocketChannel channel=(SocketChannel)sk.channel();
        EchoClient echoClient=(EchoClient)sk.attachment();
        LinkedList<ByteBuffer> outq=echoClient.getOutputQueue();

        ByteBuffer bb=outq.getLast();
        try {
            int len=channel.write(bb);
            if(len==-1){
                disconnect(sk);
                return;
            }
            if(bb.remaining()==0){
                outq.removeLast();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("fail to write to client");
            disconnect(sk);
        }

        if(outq.size()==0){
            sk.interestOps(SelectionKey.OP_READ);
        }

    }
    private void disconnect(SelectionKey sk){
        SocketChannel sc=(SocketChannel)sk.channel();
        try {
            sc.finishConnect();
        }catch (IOException e){

        }
    }



    private void startServer() throws Exception{
        //声明一个selector
        selector= SelectorProvider.provider().openSelector();

        //声明一个server socket channel,而且是非阻塞的。
        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);

//        InetSocketAddress isa=new InetSocketAddress(InetAddress.getLocalHost(),8000);
        //声明服务器端的端口
        InetSocketAddress isa=new InetSocketAddress(8000);
        //服务器端的socket channel绑定在这个端口。
        ssc.socket().bind(isa);
        //把一个socketchannel注册到一个selector上，同时选择监听的事件，SelectionKey.OP_ACCEPT表示对selector如果
        //监听到注册在它上面的server socket channel准备去接受一个连接，或 有个错误挂起，selector将把OP_ACCEPT加到
        //key ready set 并把key加到selected-key set.
        SelectionKey acceptKey=ssc.register(selector,SelectionKey.OP_ACCEPT);

        for(;;){
            selector.select();
            Set readyKeys=selector.selectedKeys();
            Iterator i=readyKeys.iterator();
            long e=0;
            while (i.hasNext()){
                SelectionKey sk=(SelectionKey)i.next();
                i.remove();

                if(sk.isAcceptable()){
                    doAccept(sk);
                }else if(sk.isValid()&&sk.isReadable()){
                    if(!geym_time_stat.containsKey(((SocketChannel)sk.channel()).socket())){
                        geym_time_stat.put(((SocketChannel)sk.channel()).socket(),System.currentTimeMillis());
                        doRead(sk);
                    }
                }else if(sk.isValid()&&sk.isWritable()){
                    doWrite(sk);
                    e=System.currentTimeMillis();
                    long b=geym_time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend"+(e-b)+"ms");
                }
            }
        }
    }

    public static void main(String[] args) {
        MultiThreadNIOEchoServer echoServer=new MultiThreadNIOEchoServer();
        try {
            echoServer.startServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
