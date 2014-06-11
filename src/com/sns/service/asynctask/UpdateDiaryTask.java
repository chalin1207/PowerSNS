package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;
import com.sns.view.DiaryMessActivity;

public class UpdateDiaryTask extends AsyncTask<String, String, String>{

	DiaryMessActivity diaryMessActivity;
	Url url;
	public UpdateDiaryTask(DiaryMessActivity diaryMessActivity){
		this.diaryMessActivity = diaryMessActivity;
		url = new Url();
	}
	@Override
	protected String doInBackground(String... arg0) {
		String URL = url.getUrl() + "/service1.asmx";
		String method_name = "UpdateDiary";
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("did", arg0[0]);
		maps.put("DContent", arg0[1]);
		
		String result = SOAPUtils.callWebServiceWithParams(URL,method_name, maps);
System.out.println("·µ»ØÖµ-*----->"+result);		
		return result;
	}

}
