package com.bgxt.datatimepickerdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AsyncTaskActivity2 extends Activity {
	private Button btnDown;
	private ImageView ivImage;
	private static String image_path = "http://ww4.sinaimg.cn/bmiddle/786013a5jw1e7akotp4bcj20c80i3aao.jpg";
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask_activity);
		btnDown = (Button) findViewById(R.id.btnDown);
		ivImage = (ImageView) findViewById(R.id.ivSinaImage);

		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在下载，请稍后...");
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(false);

		btnDown.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//  执行异步任务
				new MyTask().execute(image_path);
			}
		});
	}

	public class MyTask extends AsyncTask<String, Integer, Bitmap> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			// 设置进度对话框的进度值
			dialog.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			dialog.dismiss();
			ivImage.setImageBitmap(result);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			InputStream inputStream = null;
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					inputStream = httpResponse.getEntity().getContent();
					long file_length = httpResponse.getEntity()
							.getContentLength();
					int len = 0;
					byte[] data = new byte[1024];
					int total_length = 0;
					// 以字节的方式读取图片数据
					while ((len = inputStream.read(data)) != -1) {
						total_length += len;
						// 计算进度
						int values = (int) ((total_length / (float) file_length) * 100);
						// 发布进度信息
						publishProgress(values);
						outputStream.write(data, 0, len);
					}
					byte[] result=outputStream.toByteArray();
					bitmap=BitmapFactory.decodeByteArray(result, 0, result.length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception e2) {
				}
			}
			return bitmap;
		}
	}
}
