package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class CreateAlbumTask extends AsyncTask<String, String, String>{

	Url url = new Url();
	@Override
	protected String doInBackground(String... arg0) {
		String URL=url.getUrl()+"/PhotoService.asmx";
		String method_name="CreateAlbum";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("user_id", arg0[0]);
		maps.put("album_name", arg0[1]);
		maps.put("descript", arg0[2]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

}
