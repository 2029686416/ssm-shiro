package com.java.thread;

/**
 * @author liuhao
 * @date 2019-11-01-9:53
 */
public class MyThread02 extends Thread {

    private int count = 5;

    @Override
    public synchronized void run() {//加上同步关键词，就不会有线程同步访问出错的问题
        super.run();
        count--;
        System.out.println("由 " + MyThread.currentThread().getName() + " 计算，count=" + count);
    }
}
class Run02 {
    public static void main(String[] args) {
        //共享数据的情况
        MyThread02 mythread=new MyThread02();
        //下列线程都是通过mythread对象创建的
        Thread a=new Thread(mythread,"A");
        Thread b=new Thread(mythread,"B");
        Thread c=new Thread(mythread,"C");
        Thread d=new Thread(mythread,"D");
        Thread e=new Thread(mythread,"E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}


