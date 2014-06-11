package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

import android.os.AsyncTask;

public class GetAbumListTask extends AsyncTask<String, String, SoapObject>{

	Url url = new Url();
	@Override
	protected SoapObject doInBackground(String... params) {
		
		String URL=url.getUrl()+"/PhotoService.asmx";
		String method_name="getAlbumList";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("userid", params[0]);
		SoapObject result=SOAPUtils.getSoapObjectMess(URL, method_name, maps);
		
		return result;
	}

}
