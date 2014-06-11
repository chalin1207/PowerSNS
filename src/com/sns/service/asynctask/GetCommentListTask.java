package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;
import org.ksoap2.serialization.SoapObject;
import android.os.AsyncTask;
import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class GetCommentListTask extends AsyncTask<String, String, SoapObject>{

	Url url;
	
	@Override
	protected SoapObject doInBackground(String... params) {
		url = new Url();
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="GetCommentList";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("did", params[0]);
System.out.println("did--->"+params[0]);		
		SoapObject result=SOAPUtils.getSoapObjectMess(URL, method_name, maps);
System.out.println("GetCommentListTask------result-->comment----->"+result);		
		return result;
	}

}
