package com.java.thread.synch;

/**
 * @author liuhao
 * @date 2019-11-01-10:07
 */
class MyRunable implements Runnable {

    /**
     * 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
     * 其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
     */
    @Override
    public void run() {
        synchronized(this) {
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

public class Demo1_1 {

    public static void main(String[] args) {
        Runnable demo = new MyRunable();     // 新建“Runnable对象”

        Thread t1 = new Thread(demo, "t1");  // 新建“线程t1”, t1是基于demo这个Runnable对象
        Thread t2 = new Thread(demo, "t2");  // 新建“线程t2”, t2是基于demo这个Runnable对象
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2”
    }
}
