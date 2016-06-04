package com.bgxt.datatimepickerdemo.thread.right;

import com.bgxt.datatimepickerdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 * @author icqapp
 * 主UI的数据是不是更新了呢，这就是线程之间的通信了
 */
public class ANR2Right extends Activity {
    private Button btn_start,btn_stop;
    private TextView tv;
    private int i=0;
    private BestRunnable brThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anr_right_activity2);
        btn_start=(Button) findViewById(R.id.btn);
        btn_stop=(Button) findViewById(R.id.btnStop);
        tv=(TextView) findViewById(R.id.tv);
        brThread=new BestRunnable();
        btn_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始发送消息
                brThread.startThread();
                handler.post(brThread);
            }
        });
        btn_stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止发送消息
                brThread.stopThread();
                handler.removeCallbacks(brThread);
            }
        });
    }
    //创建新的线程
    public class BestRunnable implements Runnable {
        private volatile boolean finished = false; // ① volatile条件变量

        public void stopThread() {
            finished = true; // ② 发出停止信号
        }

        public void startThread() {
            finished = false; // ② 发出启动信号
        }
        @Override
        public void run() {
            if (!finished) {
                i++;
                // 使用Message封装非UI线程的消息
                Message m = new Message();
                // 使用Message的arg1属性或者arg2属性传递int类型的消息效率高
                m.arg1 = i;
                // 使用Handler发送消息
                handler.sendMessage(m);
            }
        }
    }





    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        public void handleMessage(Message m){
            System.out.println(m.arg1);
            String str=m.arg1+"";
            //注意：一定一定要记得TextView是String类型哦
            tv.setText(str);
            handler.post(brThread);
        }
    };
}