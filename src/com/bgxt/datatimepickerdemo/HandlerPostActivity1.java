package com.bgxt.datatimepickerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerPostActivity1 extends Activity {
	private Button btnMes1,btnMes2;
	private TextView tvMessage;
	// 声明一个Handler对象
	private static Handler handler=new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_activity);

		btnMes1=(Button)findViewById(R.id.btnMes1);
		btnMes2=(Button)findViewById(R.id.btnMes2);
		tvMessage=(TextView)findViewById(R.id.tvMessage);
		btnMes1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 新启动一个子线程
				new Thread(new Runnable() {
					@Override
					public void run() {
						// tvMessage.setText("...");
						// 以上操作会爆错，无法再子线程中访问UI组件，UI组件的属性必须在UI线程中访问
						// 使用post方式修改UI组件tvMessage的Text属性
						handler.post(new Runnable() {
							@Override
							public void run() {
								tvMessage.setText("使用Handler.post在工作线程中发送一段执行到消息队列中，在主线程中执行。");
							}
						});
					}
				}).start();
			}
		});

		btnMes2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// 使用postDelayed方式修改UI组件tvMessage的Text属性值
						// 并且延迟3S执行
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								tvMessage.setText("使用Handler.postDelayed在工作线程中发送一段执行到消息队列中，在主线程中延迟3S执行。");

							}
						}, 3000);
					}
				}).start();

			}
		});
	}
}
