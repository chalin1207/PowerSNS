package com.sns.service.buttonlistener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.powersns.R;
import com.sns.service.asynctask.LoginTask;
import com.sns.view.LoginActivity;
import com.sns.view.RegisterActivity;

public class LoginButtonListener implements OnClickListener{
	LoginTask loginTask;
	LoginActivity loginActivity;
	  
	
	public LoginButtonListener(LoginActivity loginActivity){
		this.loginActivity = loginActivity;
		//this.loginTask = loginTask;
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.login){
			loginActivity.mSaveDialog = ProgressDialog.show(loginActivity, "µÇÂ¼", "µÇÂ¼ÖÐ£¬ÇëÉÔµÈ...", true);
		new LoginTask(loginActivity).execute(loginActivity.et_username.getText().toString(),loginActivity.et_password.getText().toString());
		}else if(v.getId()==R.id.register){
			Intent intent = new Intent();
			intent.setClass(loginActivity,RegisterActivity.class);
			loginActivity.startActivity(intent);
			loginActivity.finish();
		}
	}

}
