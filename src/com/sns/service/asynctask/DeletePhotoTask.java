package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;


import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class DeletePhotoTask extends AsyncTask<String, String, String>{

	Url url = new Url();
	@Override
	protected String doInBackground(String... params) {
		String URL=url.getUrl()+"/PhotoService.asmx";
		String method_name="DeletePhoto";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("photo_id", params[0]);
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);
		
		return result;
	}

}
