package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import com.sns.bean.Url;
import com.sns.util.SOAPUtils;
import com.sns.view.LoginActivity;
import com.sns.view.RegisterActivity;

public class RegisterTask extends AsyncTask<String, String, String>{

	RegisterActivity registerActivity;
	Url url;
	String result;
	public RegisterTask(RegisterActivity registerActivity){
		this.registerActivity = registerActivity;
		url = new Url();
	}
	
	@Override
	protected String doInBackground(String... arg0) {
System.out.println("url" + url.getUrl());
		String URL = url.getUrl() + "/service1.asmx";
		String method_name = "RegisterNewAccount";
		if (arg0[1].equals(arg0[2])) {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("UID", arg0[0]);
			maps.put("Password", arg0[1]);
			maps.put("NickName", arg0[3]);

			result = SOAPUtils.callWebServiceWithParams(URL,method_name, maps);
		} else {
            result = "两次输入的密码不一样~";
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(result.equals("true")){
			Toast.makeText(registerActivity, "注册成功~！", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent();
			intent.setClass(registerActivity, LoginActivity.class);
			registerActivity.startActivity(intent);
		}else if(result == "false"){
			Toast.makeText(registerActivity, "帐号已存在~！", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(registerActivity, result, Toast.LENGTH_SHORT).show();
		}
	}

}
