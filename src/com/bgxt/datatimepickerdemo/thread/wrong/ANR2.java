package com.bgxt.datatimepickerdemo.thread.wrong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.bgxt.datatimepickerdemo.R;

/**
 * 由于单线程而导致的ANR错误
 *
 * @author icqapp 把利用新的线程来更新主UI线程(错误)
 * 说明其他线程是不能更改主UI的数据的
 * Only the original thread that created a view hierarchy can touch its views.
 */
public class ANR2 extends Activity {
	private Button btn;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anr_activity1);
		btn = (Button) findViewById(R.id.btn);
		tv = (TextView) findViewById(R.id.tv);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						int count = 0;
						while (count < 1000) {
							count++;
							tv.setText(count + "");// 试图更新主UI线程
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();// 启动线程
			}
		});
	}
}
