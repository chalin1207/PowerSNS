package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class DeleteDiaryMessTask extends AsyncTask<String, String, String>{

	Url url;
	
	public DeleteDiaryMessTask(){
		url = new Url();
	}
	@Override
	protected String doInBackground(String... params) {
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="DeleteDiary";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("did", params[0]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

}
