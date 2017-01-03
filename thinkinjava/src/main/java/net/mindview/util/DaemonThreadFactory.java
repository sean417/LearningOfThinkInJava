//: net/mindview/util/DaemonThreadFactory.java
package net.mindview.util;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {
  public Thread newThread(Runnable r) {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  }
} ///:~
