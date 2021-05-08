package com.example.sharelearnproject.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharelearnproject.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1：进程的含义
 * 进程是程序运行资源分配的最小单位
 * 进程是操作系统进行资源分配的最小单位,其中资源包括:CPU、内存空间、 磁盘 IO 等,同一进程中的多条线程共享该进程中的全部系统资源,
 * 而进程和进程 之间是相互独立的。进程是具有一定独立功能的程序关于某个数据集合上的一次 运行活动,进程是系统进行资源分配和调度的一个独立单位。
 * 进程是程序在计算机上的一次执行活动。当你运行一个程序,你就启动了一 个进程。显然,程序是死的、静态的,进程是活的、动态的。
 * 进程可以分为系统进 程和用户进程。凡是用于完成操作系统的各种功能的进程就是系统进程,它们就 是处于运行状态下的操作系统本身,用户进程就是所有由你启动的进程。
 *
 * 2：线程的含义
 * 线程是 CPU 调度的最小单位,必须依赖于进程而存在 线程是进程的一个实体,是 CPU 调度和分派的基本单位,它是比进程更小的、 能独立运行的基本单位。
 * 线程自己基本上不拥有系统资源,只拥有一点在运行中 必不可少的资源(如程序计数器,一组寄存器和栈),但是它可与同属一个进程的其 他的线程共享进程所拥有的全部资源。
 * 线程无处不在 任何一个程序都必须要创建线程,特别是 Java 不管任何程序都必须启动一个 main 函数的主线程;
 * Java Web 开发里面的定时任务、定时器、JSP 和 Servlet、异 步消息处理机制,远程访问接口RM等,任何一个监听事件, onclick的触发事件等都 离不开线程和并发的知识。
 *
 * 3：时间片轮转机制（rr）
 * 时间片执行一条指令的时间很短，不同进程进行轮转时需要释放资源，切换上下文，非常消耗资源。所以多线程开发时需要尽量避免上下文切换。
 *
 * 4：并行和并发。
 * 并行：指应用能够同时执行不同的任务,例:吃饭的时候可以边吃饭边打电话, 这两件事情可以同时执行。
 * 并发：必须结合时间单位来说明。指应用能够交替执行不同的任务,比如单 CPU 核心下执行多线程并非是 同时执行多个任务,如果你开两个线程执行,就是在你几乎不可能察觉到的速度不 断去切换这两个任务,已达到"同时执行效果",
 * 其实并不是的,只是计算机的速度太 快,我们无法察觉到而已.（时间片轮转机制就是并发）。
 *
 * 5：高并发（多线程）编程的好处
 * ①：充分利用CPU资源
 * ②：加快相应用户的时间
 * ③：使代码模块化，异步化，简单化。
 * 例如：我们实现电商系统，下订单和给用户发送短信、邮件就可以进行拆分， 将给用户发送短信、邮件这两个步骤独立为单独的模块，并交给其他线程去执行。
 * 这样既增加了异步的操作，提升了系统性能，又使程序模块化,清晰化和简单化。
 *
 * 6：高并发（多线程）编程可能遇见的问题。
 * ①：线程安全性。因为一个进程内多个线程是共享资源的，如果多个线程对同一个对象同时进行数据写入时会导致线程安全问题。
 * ②：线程间的死锁/锁消耗资源导致性能下降。为了解决线程安全问题引入Java锁实现线程同步，可能导致死锁或者性能降低。
 * ③：进程中的线程数量是有限制的。Linux：1000个。Windows：2000个。每次创建一个线程就会占用1m内存，过多的线程创建会导致资源浪费严重，系统崩溃。
 *   所以引入线程池。
 *
 *   7:创建thread有两种方法。
 *   ①：new Thread()对象。调用start。
 *   ②：implements Runnable，作为参数创建thread，调用start。(实现callable不算第三种)
 *
 *   8：Thread和runnable区别。
 *   thread是对线程的抽象。runnable是对任务的抽象。
 *
 *   9:线程停止所调用的过时方法（不建议使用），以及原因。
 *   例如
 *     stop：线程占用资源不能及时释放。会造成资源损坏。
 *     suspend：挂起。如果当前线程持有锁，调用suspend后会形成死锁。
 *     resume同理。
 *
 *   10：线程正确的停止方法。
 *   首先要明白。jdk中的线程是协作试（interrupt），不是抢占式的（stop）。
 *   mThread.interruput():设置停止标志位。不会立即停止。
 *   mThread.isInterrupted():返回Boolean值，判断是否有标志位。调用interruput后，返回值是true。
 *   Thread.interrupted():静态方法，返回Boolean值，也是判断是否有标志位。调用interruput后，返回值仍是false。因为该方法会擦除标记。
 *
 *   11:为什么不可以自己设置标识位来判读是否停止线程。
 *   while(getCancel()){
 *       Thread.sleep();或者mThread.wait(),take()方法时是不会走外部while的判读条件的。
 *   }
 *
 *
 *
 *
 * */
public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        callableThreadTest();
        boolean interrupted = Thread.interrupted();


    }


    /**
     * 通过callable方式创建thread
     */
    public void callableThreadTest(){
        MyCallable myCallable = new MyCallable();
        /**
         * RunnableFuture接口继承了Runnable，Future接口（接口可以多继承，类只能单继承）
         * FutureTask实现了RunnableFuture
         */
        FutureTask<Integer> futureTask = new FutureTask(myCallable);
        new Thread(futureTask).start();
        try {
            int integer = futureTask.get();//1
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    /**
     * callable有返回值。作为FutureTask形参。
     */
    static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }
}
