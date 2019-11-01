package com.java.thread.join;

/**
 * @author liuhao
 * @date 2019-11-01-13:33
 */
// JoinTest.java的源码
public class JoinTest{

    public static void main(String[] args){
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”

            t1.start(); // 启动“线程t1”
            //查看join源码会发现有wait，是让当前正在执行的 main线程等待
            t1.join();  // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name){
            super(name);
        }
        public void run(){
            System.out.printf("%s start\n", this.getName());

            // 延时操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s finish\n", this.getName());
        }
    }
}
