package com.example.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {// ���������͵ķ��ز��������巺��Ҫע��Void�Ǵ�д

	@Override
	protected void onPreExecute() {
		Log.v("ang", "onPreExecute");
		super.onPreExecute();
		
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		Log.v("ang", "doInBackground");
		publishProgress();// �������ֵ
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		Log.v("ang", "onPostExecute");
		super.onPostExecute(result);
	}

	// ��ȡ���ȣ����½�����
	@Override
	protected void onProgressUpdate(Void... values) {
		Log.v("ang", "onProgressUpdate");
		super.onProgressUpdate(values);
	}

}
