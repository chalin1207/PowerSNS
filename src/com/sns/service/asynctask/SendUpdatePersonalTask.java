package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;
import com.sns.view.UpdatePersonalActivity;

import android.os.AsyncTask;

public class SendUpdatePersonalTask extends AsyncTask<String, String, String>{

	UpdatePersonalActivity updatePersonalActivity;
	Url url;
	String result;
	public SendUpdatePersonalTask(UpdatePersonalActivity updatePersonalActivity){
		this.updatePersonalActivity = updatePersonalActivity;
		url = new Url();
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		
		String URL = url.getUrl() + "/service1.asmx";
		String method_name = "UpdateUserInfo";
		
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("UID", arg0[0]);
		maps.put("_name", arg0[1]);
		maps.put("_age", arg0[2]);
		maps.put("_gender", arg0[3]);
		maps.put("_mood", arg0[4]);
		
		result = SOAPUtils.callWebServiceWithParams(URL,method_name, maps);
System.out.println("SendUpdatePersonalTask-----result---->"+result);		
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	
}
