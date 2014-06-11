package com.sns.service.asynctask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;

import com.sns.bean.Url;
import com.sns.util.ImageDispose;
import com.sns.util.SOAPUtils;
import com.sns.view.ChangeAlbumPhotoActivity;

public class UpLoadAlbumPhotoTask extends AsyncTask<String, String, String>{

	ChangeAlbumPhotoActivity changeAlbumPhotoActivity;
	Url url;
	
	public UpLoadAlbumPhotoTask(ChangeAlbumPhotoActivity changeAlbumPhotoActivity){
		this.changeAlbumPhotoActivity = changeAlbumPhotoActivity;
		url = new Url();
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		
		String URL=url.getUrl()+"/PhotoService.asmx";
		
		String method_name="UploadPhotoToAlbum_base64"; 
		Map<String,String> map=new HashMap<String,String>();
		map.put("userid", arg0[0]);  
		map.put("albumid", arg0[1]);
		map.put("FileName", getFilname()+".jpg");
		map.put("descript", arg0[2]);
	    
		String image_base64= ImageDispose.getImageBase64FromBitmap(changeAlbumPhotoActivity.targetPng);
		
		String result=SOAPUtils.UploadImageWebService(URL, method_name, map, image_base64);
		
		
		return result;
	}
	
	protected String getFilname(){
		SimpleDateFormat si = new SimpleDateFormat("HH.mm.ss");
		String dates = si.format(new Date());
		return dates;
	}
	
	
}
