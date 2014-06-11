package com.sns.service.buttonlistener;

import com.sns.view.LoginActivity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class LoginCheckBoxListener implements OnCheckedChangeListener{

	LoginActivity loginActivity;
	
	public LoginCheckBoxListener(LoginActivity loginActivity){
		this.loginActivity = loginActivity;
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(buttonView.isChecked()){
			loginActivity.sp.edit().putBoolean("isCheck", true).commit();
		}else{
			loginActivity.sp.edit().putBoolean("isCheck", false).commit();
		}
	}
	

}
