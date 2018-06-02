package com.learning.patterDesign.command;

public class ConcreteCommand1 extends Command {
    //对哪个receiver类进行命令处理
    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        super();
        this.receiver = receiver;
    }

    //必须实现一个命令
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.println("ConcreteCommand1---execute");
        receiver.doSomething();
    }

}