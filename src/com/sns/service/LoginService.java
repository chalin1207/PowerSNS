package com.sns.service;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;

import com.sns.util.SOAPUtils;
import com.sns.view.LoginActivity;

public class LoginService {
	
	LoginActivity loginActivity;
	
	public LoginService(LoginActivity loginActivity){
		this.loginActivity = loginActivity;
	}
	
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new LoginTask().execute();
		}
		
	}
	
	public class LoginThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public class LoginTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg0) {
			String URL="http://192.168.0.52/MyWebService/service1.asmx";
			String method_name="LoginCheck";
			Map<String,String> maps=new HashMap<String,String>();
			maps.put("UID", arg0[0]);
			maps.put("password", arg0[1]);
			
			String result = SOAPUtils.callWebServiceWithParams(URL, method_name, maps);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
	}
}
