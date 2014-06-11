package com.sns.service.buttonlistener;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.powersns.R;
import com.sns.view.AlbumListActivity;
import com.sns.view.DiaryActivity;
import com.sns.view.FriendsListActivity;
import com.sns.view.LoginActivity;
import com.sns.view.PersonalActivity;
import com.sns.view.UpdatePasswordActivity;
import com.sns.view.UpdatePersonalActivity;

public class PersonalButtonListeners implements OnClickListener {

	PersonalActivity personalActivity;
	
	public PersonalButtonListeners(PersonalActivity personalActivity){
		this.personalActivity = personalActivity;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.update_mess){
			Intent intent = new Intent();
			intent.setClass(personalActivity, UpdatePersonalActivity.class);
			intent.putExtra("UID", personalActivity.Mess);
			intent.putExtra("AGE", personalActivity.AGE);
			intent.putExtra("GENDER", personalActivity.GENDER);
			intent.putExtra("NAME", personalActivity.userinfo[0]);
			intent.putExtra("MOOD", personalActivity.userinfo[1]);
			intent.putExtra("bbm", personalActivity.bbm);
			personalActivity.startActivity(intent);
			personalActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
			
		}if(v.getId()==R.id.update_pass){
			Intent intent = new Intent();
			intent.setClass(personalActivity, UpdatePasswordActivity.class);
			intent.putExtra("UID", personalActivity.Mess);
			intent.putExtra("username",personalActivity.tv_username.getText().toString());
			personalActivity.startActivity(intent);
			personalActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
			
			
		}if(v.getId()==R.id.IB_blog){
			Intent intent = new Intent();
			intent.setClass(personalActivity, DiaryActivity.class);
			intent.putExtra("UID", personalActivity.Mess);
			personalActivity.startActivity(intent);
			personalActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
			
		}if(v.getId()==R.id.IB_friend){
			Intent intent = new Intent();
			intent.setClass(personalActivity, FriendsListActivity.class);
			intent.putExtra("UID", personalActivity.Mess);
			personalActivity.startActivity(intent);
			personalActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
			
		}if(v.getId()==R.id.IB_friends_free){
			
		}if(v.getId()==R.id.IB_photo){
			Intent intent = new Intent();
			intent.setClass(personalActivity, AlbumListActivity.class);
			intent.putExtra("UID", personalActivity.Mess);
			personalActivity.startActivity(intent);
			personalActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
			
		}if(v.getId()==R.id.IB_vis){
			
		}if(v.getId()==R.id.IB_music){
			
		}if(v.getId()==R.id.btn_zhuxiao){
			Intent intent = new Intent();
			intent.setClass(personalActivity, LoginActivity.class);
			personalActivity.finish();
			personalActivity.startActivity(intent);
			
		}
	}

}
