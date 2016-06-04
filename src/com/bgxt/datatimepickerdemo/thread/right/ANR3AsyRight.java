package com.bgxt.datatimepickerdemo.thread.right;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bgxt.datatimepickerdemo.R;
/**
 * @author icqapp
 * AsyncTask创建了一个后台进程来处理耗时功能，不影响主UI的正常功能的处理。
 *
 */
public class ANR3AsyRight extends Activity {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anr_asy_right_activity);
        btn1=(Button) findViewById(R.id.btn);
        btn2=(Button) findViewById(R.id.btnOther);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建AsyncTask并执行
                new MyAsyncTask().execute();
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello To EveryOne");
            }
        });
    }


    class MyAsyncTask extends AsyncTask<Void, Void, String>{
        //核心方法，在后台启动一个线程
        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}