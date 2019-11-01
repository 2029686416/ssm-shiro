package com.java.thread.waitAndNotify;

/**
 * @author liuhao
 * @date 2019-11-01-10:56
 */
// WaitTest.java的源码
class ThreadA extends Thread{

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }
}

public class WaitTest {

    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");

        synchronized(t1) {//获取t1的锁，占用着t1锁 t1线程要等待释放锁才能执行方法
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");
                t1.wait();

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
