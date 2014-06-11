package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

import android.os.AsyncTask;

public class GetDiaryListTask extends AsyncTask<String, String, SoapObject>{

	Url url = new Url();
	
	@Override
	protected SoapObject doInBackground(String... params) {
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="GetDiaryList";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("UID", params[0]);
		
		SoapObject result=SOAPUtils.getSoapObjectMess(URL, method_name, maps);
		
		return result;
	}
	
	

}
