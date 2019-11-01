package com.java.thread.sleep;

/**
 * @author liuhao
 * @date 2019-11-01-13:25
 */
// SleepTest.java的源码
class ThreadA extends Thread{
    public ThreadA(String name){
        super(name);
    }
    public synchronized void run() {
        try {
            for(int i=0; i <10; i++){
                System.out.printf("%s: %d\n", this.getName(), i);
                // i能被4整除时，休眠100毫秒
                if (i%4 == 0)
                    Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SleepTest{
    public static void main(String[] args){
        ThreadA t1 = new ThreadA("t1");
        t1.start();
    }
}
