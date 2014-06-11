package com.sns.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.serialization.SoapObject;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.powersns.R;
import com.sns.bean.Url;
import com.sns.service.asynctask.GetPhotoListTask;

public class PhotoListActivity extends Activity {

	public Button btn_change, btn_back;
	public TextView tv_album_name;
	public ListView ListView_PhotoList;
	String UID;
	String AlbumName;
	SoapObject detail = null;
	String fileImgPath = null;
	String photo_id = null;
	ImageView[] imageView;
	Url url;

	public void init() {
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
		
		url = new Url();
		
		AlbumName = extras.getString("Album_name");
		btn_change = (Button) findViewById(R.id.Photolist_change);
		btn_back = (Button) findViewById(R.id.photolist_back);
		ListView_PhotoList = (ListView) findViewById(R.id.listView_Photoslist);

		btn_back.getBackground().setAlpha(100);
		
		tv_album_name = (TextView) findViewById(R.id.PhotoList_Albumname);
		tv_album_name.setText(AlbumName);

		btn_back.setOnClickListener(new ButtonListener());
		btn_change.setOnClickListener(new ButtonListener());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photolist_layout);
		init();
		setListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.photolist_back) {
				Intent intent = new Intent();
				intent.setClass(PhotoListActivity.this, AlbumListActivity.class);
				intent.putExtra("UID", UID);
				PhotoListActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
				PhotoListActivity.this.finish();

			}
			if (v.getId() == R.id.Photolist_change) {
				Intent intent = new Intent();
				intent.setClass(PhotoListActivity.this,
						ChangeAlbumPhotoActivity.class);
				intent.putExtra("UID", UID);
				intent.putExtra("AlbumName", AlbumName);
				PhotoListActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
				PhotoListActivity.this.finish();
			}
		}

	}

	public List getList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			SoapObject result = new GetPhotoListTask().execute(
					"'" + AlbumName + "'").get();
			detail = (SoapObject) result.getProperty("getPhotoListResult");

			for (int i = 0; i < detail.getPropertyCount(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				SoapObject detail3 = (SoapObject) detail.getProperty(i);
				fileImgPath = detail3.getProperty(3).toString();
				photo_id = detail3.getProperty(0).toString();
				
				System.out.println(detail3.getProperty(3).toString() + "取到了呵呵");
				System.out.println("图片路径为        " + fileImgPath);
				map.put("delect_photo", fileImgPath);
				map.put("photo_id", photo_id);
				DelayTask dt = new DelayTask();
				dt.execute("");
				list.add(map);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void setListView() {
		SimpleAdapter adapter = new SimpleAdapter(
				PhotoListActivity.this,
				getList(), 
				R.layout.photolist_layout_style,
				new String[] { "delect_photo" },
				new int[] { R.id.photolist_style });

		ListView_PhotoList.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	class DelayTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String result) {
			System.out.println("Item长度        :" + detail.getPropertyCount());

			imageView = new ImageView[detail.getPropertyCount()];

			for (int i = 0; i < detail.getPropertyCount(); i++) {
				System.out.println("goint to create task!");
				View temp = ListView_PhotoList.getChildAt(i);
				System.out.println("success in getChildAt!");
				if (temp != null) {
					System.out.println("List Item " + i + " found!");
				} else {
					System.out.println("List Item " + i + " not found!");
				}

				imageView[i] = (ImageView) temp.findViewById(R.id.photolist_style);
				
				if (imageView[i] != null) {
					System.out.println("imageView " + i + " found!");
				} else {
					System.out.println("imageView " + i + " not found!");

				}

				try {
					imageView[i].setImageBitmap(new DownloadImage().execute(
							url.getUrl() + "/PhotoFile/" + fileImgPath).get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}
	
	public class DownloadImage extends AsyncTask<String, Integer, Bitmap> {
		int target = 0;

		@Override
		protected Bitmap doInBackground(String... arg0) {

			String url = arg0[0];
			// String imagePath=arg0[1];

			// TODO Auto-generated method stub
			HttpClient hc = new DefaultHttpClient();

			// 提供get的位置

			HttpGet hg = new HttpGet(url);
			Bitmap bmp = null;

			try {

				HttpResponse hr = hc.execute(hg);

				InputStream is = hr.getEntity().getContent();

				byte[] data = readStream(is);

				bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
				System.out.println("图片                   +" + bmp);
				return bmp;

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			// imageView[target].setImageBitmap(result);

		}
		
		public byte[] readStream(InputStream inStream) throws Exception {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			outStream.close();
			inStream.close();
			return outStream.toByteArray();
		}
}
	
	
	

	}
