package com.bgxt.datatimepickerdemo.thread.wrong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bgxt.datatimepickerdemo.R;
/**
 * 由于单线程而导致的ANR错误
 * @author icqapp
 * ANR错误（Application Not Responding），指主UI进程被阻塞超过5秒钟而出现的错误，它会终止程序的正常运行，我们要避免它 ，而产生ANR错误的原因就是：单线程
 *
 */
public class ANR1 extends Activity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anr_activity1);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int count = 0;
				while (count < 1000) {
					count++;
					try {
						System.out.println("输出："+count);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}
}
