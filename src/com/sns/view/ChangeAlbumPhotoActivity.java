package com.sns.view;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powersns.R;
import com.sns.service.asynctask.UpLoadAlbumPhotoTask;

public class ChangeAlbumPhotoActivity extends Activity{
	
	public Button btn_back,btn_choose,btn_upload;
	public ImageView img_photo;
	public EditText ed_deri;
	public TextView tv_Albumname;
	public String UID;
	public String AlbumName;
	public Bitmap targetPng;
	
	public void init(){
		Bundle ext = getIntent().getExtras();
		UID = ext.getString("UID");
		AlbumName = ext.getString("AlbumName");
		
		btn_back = (Button)findViewById(R.id.ChangePhotoList_back);
		btn_choose = (Button)findViewById(R.id.ChangePhoto_choose);
		btn_upload = (Button)findViewById(R.id.ChangePhoto_upload);
		ed_deri = (EditText)findViewById(R.id.ChangePhoto_deri);
		img_photo = (ImageView)findViewById(R.id.imageView_choose);
		
		tv_Albumname = (TextView)findViewById(R.id.tv_Albumname);
		
		tv_Albumname.setText(AlbumName);
		
		btn_back.setOnClickListener(new ButtonListener());
		btn_choose.setOnClickListener(new ButtonListener());
		btn_upload.setOnClickListener(new ButtonListener());
		
		btn_back.getBackground().setAlpha(100);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changephotolist_layout);
		init();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class ButtonListener implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.ChangePhotoList_back){
				Intent uu = new Intent();
				uu.setClass(ChangeAlbumPhotoActivity.this, PhotoListActivity.class);
                uu.putExtra("UID", UID);		
                uu.putExtra("Album_name", AlbumName);
                ChangeAlbumPhotoActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
				
			}if(v.getId()==R.id.ChangePhoto_choose){
				Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 1);
				
			}if(v.getId()==R.id.ChangePhoto_upload){
				try {
					String result = new UpLoadAlbumPhotoTask(ChangeAlbumPhotoActivity.this).execute(UID,AlbumName,ed_deri.getText().toString()).get();
                    if(result.equals("SUCCESS..")){
                    	Toast.makeText(ChangeAlbumPhotoActivity.this, "图片上传相片成功", Toast.LENGTH_SHORT).show();
					}if(result.equals("SERVICE ERROR:ALBUMS NOT EXIST..")){
						Toast.makeText(ChangeAlbumPhotoActivity.this, "相册不存在", Toast.LENGTH_SHORT).show();
					}if(result.equals("SERVICE ERROR:FILE SAVE FAILURE...")){
						Toast.makeText(ChangeAlbumPhotoActivity.this, "文件保存失败", Toast.LENGTH_SHORT).show();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 1)   
		{  
			if (resultCode == RESULT_OK) 
			{
				//从手机中读取相片文件
				Uri imageFileUri = data.getData();
		        targetPng=null;
		        targetPng = getBitmapFromUri(imageFileUri);
		        
		        img_photo.setImageBitmap(targetPng);
		        
		        
			}
	    }
		
	}
	
	//资源转为图片
		public  Bitmap getBitmapFromUri(Uri uri)
	    {
	     try
	     {
	      // 读取uri所在的图片
	      Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
	      return bitmap;
	     }
	     catch (Exception e)
	     {
	      Log.e("[Android]", e.getMessage());
	      Log.e("[Android]", "目录为：" + uri);
	      e.printStackTrace();
	      return null;
	     }
	    }
	
	
}
