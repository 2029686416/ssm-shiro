package com.java.thread.synch;

/**
 * @author liuhao
 * @date 2019-11-01-10:16
 */
class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public  void run() {
        System.out.println("this"+this);
        synchronized(this) {//获取不同对象t1,t2，此处加不加同步锁都一样
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}

public class Demo1_2 {

    public static void main(String[] args) {
        Thread t1 = new MyThread("t1");  // 新建“线程t1”
        Thread t2 = new MyThread("t2");  // 新建“线程t2”
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }
}
