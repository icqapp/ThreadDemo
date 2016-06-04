package com.bgxt.datatimepickerdemo;

import com.bgxt.datatimepickerdemo.thread.right.ANR1Right;
import com.bgxt.datatimepickerdemo.thread.right.ANR2Right;
import com.bgxt.datatimepickerdemo.thread.right.ANR3AsyDownLoad;
import com.bgxt.datatimepickerdemo.thread.right.ANR3AsyRight;
import com.bgxt.datatimepickerdemo.thread.wrong.ANR1;
import com.bgxt.datatimepickerdemo.thread.wrong.ANR2;
import com.bgxt.datatimepickerdemo.thread.wrong.ANR3Asy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnId1,btnId2,btnId3,btnId4,btnId5,btnId6;
	
	private Button btnId61,btnId7,btnId8,btnId9,btnId10,btnId11,btnId12;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnId1=(Button)findViewById(R.id.btnId1);
		btnId2=(Button)findViewById(R.id.btnId2);
		btnId3=(Button)findViewById(R.id.btnId3);
		btnId4=(Button)findViewById(R.id.btnId4);
		btnId5=(Button)findViewById(R.id.btnId5);
		btnId6=(Button)findViewById(R.id.btnId6);
		btnId1.setOnClickListener(this);
		btnId2.setOnClickListener(this);
		btnId3.setOnClickListener(this);
		btnId4.setOnClickListener(this);
		btnId5.setOnClickListener(this);
		btnId6.setOnClickListener(this);
		
		btnId61=(Button)findViewById(R.id.btnId61);
		btnId7=(Button)findViewById(R.id.btnId7);
		btnId8=(Button)findViewById(R.id.btnId8);
		btnId9=(Button)findViewById(R.id.btnId9);
		btnId10=(Button)findViewById(R.id.btnId10);
		btnId11=(Button)findViewById(R.id.btnId11);
		btnId12=(Button)findViewById(R.id.btnId12);
		btnId61.setOnClickListener(this);
		btnId7.setOnClickListener(this);
		btnId8.setOnClickListener(this);
		btnId9.setOnClickListener(this);
		btnId10.setOnClickListener(this);
		btnId11.setOnClickListener(this);
		btnId12.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=null;
		switch (v.getId()) {
		case R.id.btnId1:
			intent=new Intent(MainActivity.this, AsyncTaskActivity1.class);
			startActivity(intent);
			break;
		case R.id.btnId2:
			intent=new Intent(MainActivity.this, AsyncTaskActivity2.class);
			startActivity(intent);
			break;
		case R.id.btnId3:
			intent=new Intent(MainActivity.this, HandlerMessageActivity1.class);
			startActivity(intent);
			break;
		case R.id.btnId4:
			intent=new Intent(MainActivity.this, HandlerPostActivity1.class);
			startActivity(intent);
			break;
		case R.id.btnId5:
			intent=new Intent(MainActivity.this, HandlerPostActivity2.class);
			startActivity(intent);
			break;
		case R.id.btnId6:
			intent=new Intent(MainActivity.this, HandlerMessageActivity2.class);
			startActivity(intent);
			break;
		case R.id.btnId61:
			intent=new Intent(MainActivity.this, ANR1.class);
			startActivity(intent);
			break;
		case R.id.btnId7:
			intent=new Intent(MainActivity.this, ANR1Right.class);
			startActivity(intent);
			break;
		case R.id.btnId8:
			intent=new Intent(MainActivity.this, ANR2.class);
			startActivity(intent);
			break;
		case R.id.btnId9:
			intent=new Intent(MainActivity.this, ANR2Right.class);
			startActivity(intent);
			break;
		case R.id.btnId10:
			intent=new Intent(MainActivity.this, ANR3Asy.class);
			startActivity(intent);
			break;
		case R.id.btnId11:
			intent=new Intent(MainActivity.this, ANR3AsyRight.class);
			startActivity(intent);
			break;
		case R.id.btnId12:
			intent=new Intent(MainActivity.this, ANR3AsyDownLoad.class);
			startActivity(intent);
			break;
			
			
			
			
		}
	}

}
