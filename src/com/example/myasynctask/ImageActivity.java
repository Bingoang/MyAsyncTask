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
		// 通过new一个西异步任务对象，调用execute方法开启异步任务线程操作。并将图片地址传进去
		new MyAsyncTask2().execute(URL);
	}

	private void initView() {
		imageView = (ImageView) findViewById(R.id.image);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

	}

	/**
	 * 定义一个内部类，三参数：
	 * 
	 * @Params:要传入一个URL,所以String;
	 * @Progress:不需要返回加载进度,所以Void;
	 * @Result:返回的是Bitmap类型
	 */
	class MyAsyncTask2 extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// 显示进度条
			progressBar.setVisibility(View.VISIBLE);
		}

		/*
		 * String... params是可变长数组，即可进可传进许多参数
		 */
		@Override
		protected Bitmap doInBackground(String... params) {
			// 获取传递进来的参数： 因为只传进一张图片，所以只用取出数组params的第0位内容
			String url = params[0];
			Bitmap bitmap = null;
			URLConnection connection;// 定义网络连接对象
			InputStream is;// 用于获取数据的输入流对象

			// 将URL解析成bitmap对象(此为耗时操作，所以在doInBackground中)
			try {
				// 将url传进去，获取网络连接对象
				connection = new URL(url).openConnection();
				// 获取输入流接对象
				is = connection.getInputStream();
				// 将输入流is放到缓存器中
				BufferedInputStream bis = new BufferedInputStream(is);
				
				Thread.sleep(3000);//人为阻碍加载
				
				// 将bis传入，通过decodeStream把输入流解析成bitmap
				bitmap = BitmapFactory.decodeStream(bis);

				// 关闭输入流
				is.close();
				bis.close();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return bitmap;// 返回的是解析得到的bitmap对象
		}

		/*
		 * 操作UI,设置图像 :
		 * 
		 * doInBackground处理完后返回结果bitmap传给onPostExecute，
		 * 所以onPostExecute中的bitmap就是解析后的图像
		 */
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			progressBar.setVisibility(View.GONE);
			imageView.setImageBitmap(bitmap);
		}
	}
}
