package com.sns.service.asynctask;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;
import com.sns.view.PostCommentDialog;

public class PostCommentTask extends AsyncTask<String, String, String>{

	PostCommentDialog postCommentDialog;
	Url url;
	
	public PostCommentTask(PostCommentDialog postCommentDialog){
		this.postCommentDialog = postCommentDialog;
		url = new Url();
	}
	@Override
	protected String doInBackground(String... params) {
		String URL=url.getUrl()+"/service1.asmx";
		String method_name="PostComment";
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("did", params[0]);
		maps.put("comcontent", params[1]);
		maps.put("fuid", params[2]);
		
		String result=SOAPUtils.callWebServiceWithParams(URL, method_name, maps);

		return result;
	}

}
