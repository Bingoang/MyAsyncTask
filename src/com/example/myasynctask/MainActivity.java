package com.example.myasynctask;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		MyAsyncTask task = new MyAsyncTask();
		task.execute();// 启动AsyncTask,类似于线程中的start()方法
	}

	private void initView() {
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(this,ImageActivity.class);
		startActivity(intent);

	}

}
