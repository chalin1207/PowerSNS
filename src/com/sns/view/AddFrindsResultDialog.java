package com.sns.view;


import java.util.concurrent.ExecutionException;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powersns.R;
import com.sns.service.asynctask.AddFriendsTask;



public class AddFrindsResultDialog extends Dialog{
	
	FriendRequestListActivity friendRequestListActivity;
	public TextView tv_username,tv_uid;
	public Button btn_submit,btn_cancel;
	
	public AddFrindsResultDialog(Context context) {
		super(context);
		friendRequestListActivity = (FriendRequestListActivity)context;
	}

	public void init(){
		tv_username = (TextView)findViewById(R.id.addfriends_dialog_username);
		tv_uid = (TextView)findViewById(R.id.addfriends_dialog_userid);
		btn_submit = (Button)findViewById(R.id.addfriends_dialog_submit);
		btn_cancel = (Button)findViewById(R.id.addfriends_dialog_cancel);
		
		btn_submit.setOnClickListener(new ButtonListener());
		btn_cancel.setOnClickListener(new ButtonListener());
		
		tv_username.setText(friendRequestListActivity.Fname);
		tv_uid.setText(friendRequestListActivity.FID);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.friendsresult_dialog);
		this.setTitle("消息：");
		init();
	}
	
	public class ButtonListener implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.addfriends_dialog_submit){
				String UID = friendRequestListActivity.UID;
				String FID = friendRequestListActivity.FID;
				String flag = "A";
				
				try {
					String result = new AddFriendsTask().execute(UID,FID,flag).get();
System.out.println("添加好友的返回值--------->"+result);
                    if(result.equals("Y")){
                    	Toast.makeText(friendRequestListActivity, "你已同意加"+friendRequestListActivity.Fname+"为好友", Toast.LENGTH_SHORT).show();
                    	friendRequestListActivity.getMess();
                    	AddFrindsResultDialog.this.dismiss();
                    }
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}if(v.getId()==R.id.addfriends_dialog_cancel){
				String UID = friendRequestListActivity.UID;
				String FID = friendRequestListActivity.FID;
				String flag = "";
				
				try {
					String result = new AddFriendsTask().execute(UID,FID,flag).get();
System.out.println("添加好友的返回值--------->"+result);
                    if(result.equals("Y")){
                    	Toast.makeText(friendRequestListActivity, "你已拒绝加"+friendRequestListActivity.Fname+"为好友", Toast.LENGTH_SHORT).show();
                    	friendRequestListActivity.getMess();
                    	AddFrindsResultDialog.this.dismiss();
                    }
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
