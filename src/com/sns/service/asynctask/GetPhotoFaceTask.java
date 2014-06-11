package com.sns.service.asynctask;

import java.io.IOException;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.SOAPUtils;

public class GetPhotoFaceTask extends AsyncTask<String, String, Bitmap>{

	Url url ;
	
	@Override
	protected Bitmap doInBackground(String... params) {
		url = new Url();
		Bitmap bm = null;
		String URL=url.getUrl()+"/"+params[0];
		try {
			bm = SOAPUtils.loadPhoto(URL);
System.out.println("GetPhotoFaceTask------>bmbm*----------------->"+bm);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bm;
	}

}
