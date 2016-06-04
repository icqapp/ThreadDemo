package com.bgxt.datatimepickerdemo.thread.wrong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bgxt.datatimepickerdemo.R;
/**
 * 由于单线程而导致的ANR错误--卡屏
 * @author icqapp
 * ANR错误（Application Not Responding），指主UI进程被阻塞超过5秒钟而出现的错误，它会终止程序的正常运行，我们要避免它 ，而产生ANR错误的原因就是：单线程
 *
 */
public class ANR3Asy extends Activity {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anr_asy_activity);
        btn1=(Button) findViewById(R.id.btn);
        btn2=(Button) findViewById(R.id.btnOther);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //超过5秒了，其实就和前面的例子一样了（ANR错误）
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello To EveryOne");
            }
        });
    }
}