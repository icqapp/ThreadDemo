package com.bgxt.datatimepickerdemo.thread.right;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bgxt.datatimepickerdemo.R;
/**
 * @author icqapp
 * AsyncTask创建了一个后台进程来处理耗时功能，不影响主UI的正常功能的处理。
 *
 */
public class ANR3AsyDownLoad extends Activity {
    private Button btn;
    private ProgressBar bar;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anr_asy_down_activity);
        btn=(Button) findViewById(R.id.btn);
        bar=(ProgressBar) findViewById(R.id.progressBar1);
        tv=(TextView) findViewById(R.id.tv);
        tv.setText("开始下载");
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MyAsyncTask my=new MyAsyncTask(tv, bar);
                my.execute("","i=","打开文件");//启动方法
            }
        });
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, String>{
        private ProgressBar bar;
        private TextView tv;
        //构造方法，初始化ProgressBar和TextView
        public MyAsyncTask(TextView tv,ProgressBar bar){
            this.bar=bar;
            this.tv=tv;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        /*
                 * 这个方法的参数不能改,其返回类型与第三个参数一致，其参数与第一个参数类型一致
                 * (non-Javadoc)
                 * @see android.os.AsyncTask#doInParams[])
                  */
        @Override
        protected String doInBackground(String...  params) {//可变参数
            String p1=params[1];
            String p2=params[2];
            int i=1;
            for(i=1;i<=100;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return p1+i+p2;//也作为下一个方法的参数
        }
        /*
         * 当异步结束时触发此方法，其参数类型与第三个参数类型一致
         * (non-Javadoc)
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            if(result!=null){
                tv.setText("下载完成"/*+result*/);
            }
            super.onPostExecute(result);
        }
        /*
         * 当异步开始的时候触发
         * (non-Javadoc)
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            System.out.println("异步开始");
            tv.setText("开始下载");
            super.onPreExecute();
        }
        /*
         * 正在处理的时候触发，与主UI线程交互，其参数与第二个参数一致
         * (non-Javadoc)
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(Integer... values) {//第二个可变参数，由上面的publishProgress方法的参数决定
            bar.setProgress(values[0]);
            tv.setText(values[0]+"%");//可变参数就是这么用的，values[1]表示publishProgress的第二个参数
            super.onProgressUpdate(values);
        }
    }
}