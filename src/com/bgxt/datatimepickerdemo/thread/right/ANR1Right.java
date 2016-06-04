package com.bgxt.datatimepickerdemo.thread.right;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bgxt.datatimepickerdemo.R;

/**
 * http://blog.163.com/xh_ding/blog/static/193903289201341685931689
 * @author icqapp 我们可以再创建一个线程去处理耗时操作，就不会阻碍主UI的操作了
 *         1、停止一个线程的最佳方法是让它执行完毕，没有办法立即停止一个线程，但你可以控制何时或什么条件下让他执行完毕
 *         2、通过条件变量控制线程的执行，线程内部检查变量状态，外部改变变量值可控制停止执行。
 *         为保证线程间的即时通信，需要使用使用volatile关键字或锁，确保读线程与写线程间变量状态一致
 */
public class ANR1Right extends Activity {
	private Button btn, btn_stop;
	private Thread thread;
	private BestThread bpThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anr_right_activity);
		btn = (Button) findViewById(R.id.btn);
		btn_stop = (Button) findViewById(R.id.btnStop);
		bpThread=new BestThread();
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				thread = new Thread(bpThread);
				thread.start();
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// stop,和destroy都是Thread类废弃了很久的方法，应该使用interrupt去通知线程状态位
				if (bpThread != null) {
					bpThread.stopThread();
					thread.interrupt();
				}

			}
		});
	}

	public class BestThread implements Runnable {
		//如果一个线程由于等待某些事件的发生而被阻塞，又该怎样停止该线程呢？
		// 这种情况经常会发生，比如当一个线程由于需要等候键盘输入而被阻塞，或者调用Thread.join()方法，或者Thread.sleep()方法，
		// 在网络中调用ServerSocket.accept()方法，或者调用了DatagramSocket.receive()方法时，都有可能导致线程阻塞，使线程处于处于不可运行状态时，
		// 即使主程序中将该线程的共享变量设置为true，但该线程此时根本无法检查循环标志，当然也就无法立即中断。这里我们给出的建议是，不要使用stop()方法，
		// 而是使用Thread提供的interrupt()方法，因为该方法虽然不会中断一个正在运行的线程，但是它可以使一个被阻塞的线程抛出一个中断异常，从而使线程提前结束阻塞状态，退出堵塞代码。
		//使用了一个Java关键字volatile，这个关键字的目的是使finished同步，也就是说在同一时刻只能由一个线程来修改finished的值。
		private volatile boolean finished = false; // ① volatile条件变量

		public void stopThread() {
			finished = true; // ② 发出停止信号
		}

		@Override
		public void run() {
			int count = 0;
			while (count < 1000 && !finished) { // ③ 检测条件变量
				// do dirty work // ④业务代码
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(count);
			}
			System.out.println(ANR1Right.class.getName() + " is exiting...");

		}
	}

}