package com.sns.service.buttonlistener;

import com.example.powersns.R;
import com.sns.service.asynctask.RegisterTask;
import com.sns.view.LoginActivity;
import com.sns.view.RegisterActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class RegisterButtonListener implements OnClickListener{

	RegisterActivity registerActivity;
	
	public RegisterButtonListener(RegisterActivity registerActivity){
		this.registerActivity = registerActivity;
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_reg){
			new RegisterTask(registerActivity).execute(registerActivity.et_usernum.getText().toString(),registerActivity.et_pass.getText().toString(),registerActivity.et_passagain.getText().toString(),registerActivity.et_username.getText().toString());
		}else if(v.getId()==R.id.btn_cancel){
			Toast.makeText(registerActivity, "回到登录界面~", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent();
			intent.setClass(registerActivity, LoginActivity.class);
			registerActivity.startActivity(intent);
			registerActivity.finish();
		}
	}

}
