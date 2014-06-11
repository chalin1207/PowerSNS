package com.sns.service.buttonlistener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.example.powersns.R;
import com.sns.service.asynctask.SendUpdatePersonalTask;
import com.sns.view.PersonalActivity;
import com.sns.view.UpdatePersonalActivity;

public class UpdatePersonalButtonListener implements OnClickListener{

	UpdatePersonalActivity updatePersonalActivity;
	PersonalActivity personalActivity;
	String Messs = null;
	
	public UpdatePersonalButtonListener(UpdatePersonalActivity updatePersonalActivity){
		this.updatePersonalActivity = updatePersonalActivity;
		Messs = updatePersonalActivity.UID;
		personalActivity = new PersonalActivity();
	}
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.update_submit){
			try {
				String UID = Messs;
				String AGE = updatePersonalActivity.et_age.getText().toString();
				String NAME = updatePersonalActivity.et_username.getText().toString();
				String MOOD = updatePersonalActivity.et_mood.getText().toString();
				String GENDER = updatePersonalActivity.getSex();
				
				String result = new SendUpdatePersonalTask(updatePersonalActivity).execute(UID,NAME,AGE,GENDER,MOOD).get();
				
				if(result.equals("T")){
					Toast.makeText(updatePersonalActivity, "修改成功~", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					intent.setClass(updatePersonalActivity, PersonalActivity.class);
					intent.putExtra("UID", Messs);
					updatePersonalActivity.startActivity(intent);
					updatePersonalActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
					updatePersonalActivity.finish();
					
				}else{
					Toast.makeText(updatePersonalActivity, "服务器繁忙，请稍后重试~", Toast.LENGTH_SHORT).show();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			
		}if(arg0.getId() == R.id.update_cancel){
			Intent intent = new Intent();
			intent.setClass(updatePersonalActivity, PersonalActivity.class);
			intent.putExtra("UID", Messs);
			updatePersonalActivity.startActivity(intent);
			updatePersonalActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
			updatePersonalActivity.finish();
			
		}if(arg0.getId() == R.id.imageB_open){
System.out.println("openFeil");
            picture pic = new picture();
            pic.Picturt_List();
		}if(arg0.getId() == R.id.imageB_camera){
System.out.println("camera");
           picture pic = new picture();
           pic.pci();
		}if(arg0.getId() == R.id.imageB_upload){
System.out.println("upload");
			
		}
	}
	

	class picture extends Handler {
		public void pci() {
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, Environment
					.getExternalStorageDirectory().getAbsolutePath());
			i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			updatePersonalActivity.startActivityForResult(i, 0);

		}

		public void Picturt_List() {
			Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
			openAlbumIntent.setType("image/*");
			updatePersonalActivity.startActivityForResult(openAlbumIntent, 1);

		}
	}
	

}

