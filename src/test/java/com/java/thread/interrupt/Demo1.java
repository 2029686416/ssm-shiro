package com.java.thread.interrupt;

/**
 * @author liuhao
 * @date 2019-11-01-13:58
 */
// Demo1.java的源码
class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int i=0;

            while (!isInterrupted()) {
                Thread.sleep(100); // 休眠100ms
                i++;
                System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
        }
    }
}

/**
 * 结果说明：
 * (01) 主线程main中通过new MyThread("t1")创建线程t1，之后通过t1.start()启动线程t1。
 * (02) t1启动之后，会不断的检查它的中断标记，如果中断标记为“false”；则休眠100ms。
 * (03) t1休眠之后，会切换到主线程main；主线程再次运行时，会执行t1.interrupt()中断线程t1。
 * t1收到中断指令之后，会将t1的中断标记设置“false”，而且会抛出InterruptedException异常。
 * 在t1的run()方法中，是在循环体while之外捕获的异常；因此循环被终止。
 */
public class Demo1 {

    public static void main(String[] args) {
        try {
            Thread t1 = new MyThread("t1");  // 新建“线程t1”
            System.out.println(t1.getName() +" ("+t1.getState()+") is new.");

            t1.start();                      // 启动“线程t1”
            System.out.println(t1.getName() +" ("+t1.getState()+") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(1000);
            t1.interrupt();
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(3000);
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
