package com.bgxt.datatimepickerdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HandlerMessageActivity1 extends Activity {
	private Button btnDown;
	private ImageView ivImage;
	private static String image_path = "http://ww4.sinaimg.cn/bmiddle/786013a5jw1e7akotp4bcj20c80i3aao.jpg";
	private ProgressDialog dialog;
	private static int IS_FINISH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask_activity);

		btnDown = (Button) findViewById(R.id.btnDown);
		ivImage = (ImageView) findViewById(R.id.ivSinaImage);

		dialog = new ProgressDialog(this);
		dialog.setTitle("提示信息");
		dialog.setMessage("正在下载，请稍后...");
		dialog.setCancelable(false);

		btnDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new MyThread()).start();
				dialog.show();
			}
		});
	}

	private  Handler handler = new Handler() {
		// 在Handler中获取消息，重写handleMessage()方法
		@Override
		public void handleMessage(Message msg) {
			// 判断消息码是否为1
			if(msg.what==IS_FINISH){
				byte[] data=(byte[])msg.obj;
				Bitmap bmp=BitmapFactory.decodeByteArray(data, 0, data.length);
				ivImage.setImageBitmap(bmp);
				dialog.dismiss();
			}
		}
	};

	public class MyThread implements Runnable {

		@Override
		public void run() {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(image_path);
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					byte[] data = EntityUtils.toByteArray(httpResponse
							.getEntity());
					// 获取一个Message对象，设置what为1
					Message msg = Message.obtain();
					msg.obj = data;
					msg.what = IS_FINISH;
					// 发送这个消息到消息队列中
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
