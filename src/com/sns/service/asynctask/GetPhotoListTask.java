package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class GetPhotoListTask extends AsyncTask<String, String, SoapObject>{

	Url url = new Url();
	@Override
	protected SoapObject doInBackground(String... arg0) {
		String URL=url.getUrl()+"/PhotoService.asmx";
		String method_name="getPhotoList";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("album_id", arg0[0]);
		
		SoapObject result=SOAPUtils.getSoapObjectMess(URL, method_name, maps);
System.out.println("GetPhotoListTask------------>"+result);

		return result;
	}
}
