package com.example.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {// 三个空类型的返回参数，定义泛型要注意Void是大写

	@Override
	protected void onPreExecute() {
		Log.v("ang", "onPreExecute");
		super.onPreExecute();
		
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		Log.v("ang", "doInBackground");
		publishProgress();// 传入进度值
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		Log.v("ang", "onPostExecute");
		super.onPostExecute(result);
	}

	// 获取进度，更新进度条
	@Override
	protected void onProgressUpdate(Void... values) {
		Log.v("ang", "onProgressUpdate");
		super.onProgressUpdate(values);
	}

}
