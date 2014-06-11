package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;
import com.sns.bean.Url;
import com.sns.util.SOAPUtils;
import com.sns.view.LoginActivity;
import com.sns.view.PersonalActivity;

public class LoginTask extends AsyncTask<String, String, String>{

	LoginActivity loginActivity;
	Url url;
	public LoginTask(LoginActivity loginActivity){
		this.loginActivity = loginActivity;
		url = new Url();
	}
	@Override
	protected String doInBackground(String... arg0) {
System.out.println("url"+url.getUrl());
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="LoginCheck";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("UID", arg0[0]);
		maps.put("password", arg0[1]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		if(result.equals("true")){
			
			if(loginActivity.cb_remdmer.isChecked()){
				Editor editor = loginActivity.sp.edit();
				editor.putBoolean("isCheck", true);
				editor.putString("username", loginActivity.et_username.getText().toString());
				editor.putString("password", loginActivity.et_password.getText().toString());
				editor.commit();
System.out.println("记住密码文件的用户名----->"+loginActivity.sp.getString("username", null));	
			}
			
			Intent intent = new Intent();
			intent.setClass(loginActivity, PersonalActivity.class);
			intent.putExtra("UID", loginActivity.et_username.getText().toString());
			loginActivity.startActivity(intent);
			
			
			
			loginActivity.mSaveDialog.dismiss();
			
			loginActivity.finish();
			
		}else{
			loginActivity.mSaveDialog.dismiss();
			Toast.makeText(loginActivity, result, Toast.LENGTH_SHORT).show();
		}
	}
	


	
}
