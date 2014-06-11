package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;
import org.ksoap2.serialization.SoapObject;
import android.os.AsyncTask;
import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class GetPersonalTask extends AsyncTask<String, String, SoapObject>{

	Url url = new Url();
	
	
	@Override
	protected SoapObject doInBackground(String... arg0) {
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="GetUserInfoById";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("UID", arg0[0]);
		
		SoapObject result=SOAPUtils.getSoapObjectMess(URL, method_name, maps);
System.out.println("GetPersonalTask----->"+result);

		return result;
	}


	
	
}
