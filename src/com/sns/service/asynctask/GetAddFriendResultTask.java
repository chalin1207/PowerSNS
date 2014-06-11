package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class GetAddFriendResultTask extends AsyncTask<String, String, String>{
	
	Url url = new Url();
	
	@Override
	protected String doInBackground(String... params) {
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="FriendRequest";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("UID1", params[0]);
		maps.put("UID2", params[1]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

}
