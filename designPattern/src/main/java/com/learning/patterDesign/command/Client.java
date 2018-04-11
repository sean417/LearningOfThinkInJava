package com.learning.patterDesign.command;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Receiver receiver1 = new ConcreteReceiver1();
        Command command1 = new ConcreteCommand1(receiver1);

        Invoker invoker = new Invoker();
        invoker.setCommand(command1);
        invoker.action();

        Receiver receiver2 = new ConcreteReceiver2();
        Command command2 = new ConcreteCommand2(receiver2);

        invoker.setCommand(command2);
        invoker.action();
    }

}