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
import com.sns.service.asynctask.WriteDiaryTask;

public class WriteDiaryActivity extends Activity{
	
	public Button btn_submit,btn_cancel;
	public EditText et_title,et_content;
	public String UID;
	
	public void init(){
		btn_submit = (Button)findViewById(R.id.Wri_submit);
		btn_cancel = (Button)findViewById(R.id.Wri_cancel);
		et_title = (EditText)findViewById(R.id.Wri_title);
		et_content = (EditText)findViewById(R.id.Wri_content);
		
		btn_submit.setOnClickListener(new WriteDiaryListener());
		btn_cancel.setOnClickListener(new WriteDiaryListener());
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_diary_layout);
		init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class WriteDiaryListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.Wri_submit){
				try {
					
					if (et_title.getText().toString().trim().equals("")|| et_content.getText().toString().trim().equals("")) {
						Toast.makeText(WriteDiaryActivity.this, "请输入内容~",Toast.LENGTH_SHORT).show();
						
					} else {
System.out.println("进来了？");
                        String result = new WriteDiaryTask(WriteDiaryActivity.this).execute(UID,et_title.getText().toString(),et_content.getText().toString()).get();
						if (result.equals("true")) {
							Toast.makeText(WriteDiaryActivity.this, "发表日志成功~",Toast.LENGTH_SHORT).show();
							Intent uu = new Intent();
							uu.setClass(WriteDiaryActivity.this,DiaryActivity.class);
							uu.putExtra("UID", UID);
							WriteDiaryActivity.this.startActivity(uu);
							overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
							WriteDiaryActivity.this.finish();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}if(v.getId() == R.id.Wri_cancel){
				Intent uu = new Intent();
				uu.setClass(WriteDiaryActivity.this, DiaryActivity.class);
                uu.putExtra("UID", UID);				
                WriteDiaryActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				WriteDiaryActivity.this.finish();
			}
		}
		
	}
}
