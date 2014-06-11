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
import com.sns.service.asynctask.GetAddFriendResultTask;

public class AddFriendActivity extends Activity{
	
	public Button btn_back,btn_add,btn_cancel;
	public EditText et_Fid;
	String UID;
	
	public void init(){
		btn_back = (Button)findViewById(R.id.AddFriendList_back);
		btn_add = (Button)findViewById(R.id.add_friend);
		btn_cancel = (Button)findViewById(R.id.add_cancel);
		et_Fid = (EditText)findViewById(R.id.et_Fid);
		btn_back.getBackground().setAlpha(100);
		
		btn_back.setOnClickListener(new ButtonListener());
		btn_add.setOnClickListener(new ButtonListener());
		btn_cancel.setOnClickListener(new ButtonListener());
		
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfriends_layout);
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
			if(v.getId() == R.id.AddFriendList_back){
				Intent uu = new Intent();
				uu.setClass(AddFriendActivity.this, FriendsListActivity.class);
                uu.putExtra("UID", UID);				
                AddFriendActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				AddFriendActivity.this.finish();
				
			}if(v.getId() == R.id.add_friend){
				try {
					String result = new GetAddFriendResultTask().execute(UID,et_Fid.getText().toString()).get();
System.out.println("好友申请发去result-------------->"+result);
                    if(result.equals("N")){
                    	Toast.makeText(AddFriendActivity.this, "该账号不存在", Toast.LENGTH_SHORT).show();
                    }if(result.equals("E")){
                    	Toast.makeText(AddFriendActivity.this, "该用户已是你的好友", Toast.LENGTH_SHORT).show();
                    }if(result.equals("R")){
                    	Toast.makeText(AddFriendActivity.this, "好友申请已经发出", Toast.LENGTH_SHORT).show();
                    }if(result.equals("A")){
                    	Toast.makeText(AddFriendActivity.this, "好友申请已接收", Toast.LENGTH_SHORT).show();
                    }if(result.equals("Y")){
                    	Toast.makeText(AddFriendActivity.this, "好友申请发出成功", Toast.LENGTH_SHORT).show();
                    }
                    
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}if(v.getId() == R.id.add_cancel){
				Intent uu = new Intent();
				uu.setClass(AddFriendActivity.this, FriendsListActivity.class);
                uu.putExtra("UID", UID);				
                AddFriendActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				AddFriendActivity.this.finish();
				}
		}
	}
	}