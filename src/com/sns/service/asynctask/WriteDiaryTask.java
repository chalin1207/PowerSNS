package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;
import com.sns.view.WriteDiaryActivity;

import android.os.AsyncTask;

public class WriteDiaryTask extends AsyncTask<String, String, String>{

	WriteDiaryActivity writeDiaryActivity;
	Url url;
	public WriteDiaryTask(WriteDiaryActivity writeDiaryActivity){
		this.writeDiaryActivity = writeDiaryActivity; 
		url = new Url();
	}
	
	@Override
	protected String doInBackground(String... params) {
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="WriteDiary";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("UID", params[0]);
		maps.put("title", params[1]);
		maps.put("content", params[2]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

}
