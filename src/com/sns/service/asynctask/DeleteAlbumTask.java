package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

import android.os.AsyncTask;

public class DeleteAlbumTask extends AsyncTask<String, String, String>{

	
	Url url = new Url();
	@Override
	protected String doInBackground(String... params) {
		String URL=url.getUrl()+"/PhotoService.asmx";
		String method_name="DeleteAlbum";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("album_id", params[0]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

}
