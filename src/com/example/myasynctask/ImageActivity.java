package com.example.myasynctask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageActivity extends Activity {
	private ImageView imageView;
	private ProgressBar progressBar;
	private static String URL = "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		initView();
		// ͨ��newһ�����첽������󣬵���execute���������첽�����̲߳���������ͼƬ��ַ����ȥ
		new MyAsyncTask2().execute(URL);
	}

	private void initView() {
		imageView = (ImageView) findViewById(R.id.image);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

	}

	/**
	 * ����һ���ڲ��࣬��������
	 * 
	 * @Params:Ҫ����һ��URL,����String;
	 * @Progress:����Ҫ���ؼ��ؽ���,����Void;
	 * @Result:���ص���Bitmap����
	 */
	class MyAsyncTask2 extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// ��ʾ������
			progressBar.setVisibility(View.VISIBLE);
		}

		/*
		 * String... params�ǿɱ䳤���飬���ɽ��ɴ���������
		 */
		@Override
		protected Bitmap doInBackground(String... params) {
			// ��ȡ���ݽ����Ĳ����� ��Ϊֻ����һ��ͼƬ������ֻ��ȡ������params�ĵ�0λ����
			String url = params[0];
			Bitmap bitmap = null;
			URLConnection connection;// �����������Ӷ���
			InputStream is;// ���ڻ�ȡ���ݵ�����������

			// ��URL������bitmap����(��Ϊ��ʱ������������doInBackground��)
			try {
				// ��url����ȥ����ȡ�������Ӷ���
				connection = new URL(url).openConnection();
				// ��ȡ�������Ӷ���
				is = connection.getInputStream();
				// ��������is�ŵ���������
				BufferedInputStream bis = new BufferedInputStream(is);
				
				Thread.sleep(3000);//��Ϊ�谭����
				
				// ��bis���룬ͨ��decodeStream��������������bitmap
				bitmap = BitmapFactory.decodeStream(bis);

				// �ر�������
				is.close();
				bis.close();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return bitmap;// ���ص��ǽ����õ���bitmap����
		}

		/*
		 * ����UI,����ͼ�� :
		 * 
		 * doInBackground������󷵻ؽ��bitmap����onPostExecute��
		 * ����onPostExecute�е�bitmap���ǽ������ͼ��
		 */
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			progressBar.setVisibility(View.GONE);
			imageView.setImageBitmap(bitmap);
		}
	}
}
