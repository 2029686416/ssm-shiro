package com.java.thread.yield;

/**
 * @author liuhao
 * @date 2019-11-01-13:14
 */
// YieldTest.java的源码
class ThreadA extends Thread{
    public ThreadA(String name){
        super(name);
    }

    /**
     * 线程结果有多种情况
     * 1：
     * “线程t1”在能被4整数的时候，并没有切换到“线程t2”。
     * 这表明，yield()虽然可以让线程由“运行状态”进入到“就绪状态”；
     * 但是，它不一定会让其它线程获取CPU执行权(即，其它线程进入到“运行状态”)，
     * 即使这个“其它线程”与当前调用yield()的线程具有相同的优先级。
     */
    public synchronized void run(){
        for(int i=0; i <10; i++){
            System.out.printf(Thread.currentThread().getName()+"%s [%d]:%d\n", this.getName(), this.getPriority(), i);
            // i整除4时，调用yield
            if (i%4 == 0)
                Thread.yield();
        }
    }
}

public class YieldTest{
    public static void main(String[] args){
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        Thread thread = new Thread(t1);
//        Thread thread1 = new Thread(t1);
        thread.start();
//        thread1.start();//yield()方法不会释放锁。   thread 和thread1同享对象，会先让其中一个执行完才会执行下个
//        t1.start();
        t2.start();
    }
}
