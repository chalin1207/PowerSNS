package com.sns.view;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.powersns.R;
import com.sns.service.asynctask.CreateAlbumTask;

public class CreateNewAlbumActivity extends Activity{
	
	public Button btn_back,btn_submit,btn_cancel;
	public EditText et_title,et_deri;
	String UID;
	
	public void init(){
		btn_back = (Button)findViewById(R.id.createAlbum_back);
		btn_submit = (Button)findViewById(R.id.createAlbum_submit);
		btn_cancel = (Button)findViewById(R.id.createAlbum_cancel);
		et_title = (EditText)findViewById(R.id.createAlbum_title);
		et_deri = (EditText)findViewById(R.id.createAlbum_deri);
		
		btn_back.getBackground().setAlpha(100);
		btn_back.setOnClickListener(new ButtonListener());
		btn_submit.setOnClickListener(new ButtonListener());
		btn_cancel.setOnClickListener(new ButtonListener());
		
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createalbum_layout);
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
			if(v.getId()==R.id.createAlbum_back){
				Intent uu = new Intent();
				uu.setClass(CreateNewAlbumActivity.this, AlbumListActivity.class);
                uu.putExtra("UID", UID);				
                CreateNewAlbumActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				CreateNewAlbumActivity.this.finish();
				
			}if(v.getId()==R.id.createAlbum_submit){
				try {
					if(et_title.getText().toString().trim().equals("")||et_deri.getText().toString().trim().equals("")){
						Toast.makeText(CreateNewAlbumActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
					} else {
						String result = new CreateAlbumTask().execute(UID,et_title.getText().toString(),et_deri.getText().toString()).get();
                        if(result.equals("SUCCESS..")){
                        	Toast.makeText(CreateNewAlbumActivity.this, "创建"+et_title.getText().toString()+"相册成功", Toast.LENGTH_SHORT).show();
                        	et_title.setText("");
                        	et_deri.setText("");
                        }else{
                        	Toast.makeText(CreateNewAlbumActivity.this, "创建"+et_title.getText().toString()+"相册失败", Toast.LENGTH_SHORT).show();
                        }
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}if(v.getId()==R.id.createAlbum_cancel){
				Intent uu = new Intent();
				uu.setClass(CreateNewAlbumActivity.this, AlbumListActivity.class);
                uu.putExtra("UID", UID);				
                CreateNewAlbumActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				CreateNewAlbumActivity.this.finish();
				
			}		
		}
		
	}
}
