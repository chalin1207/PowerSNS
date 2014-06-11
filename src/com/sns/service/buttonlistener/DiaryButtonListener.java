package com.sns.service.buttonlistener;

import java.util.concurrent.ExecutionException;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.powersns.R;
import com.sns.service.asynctask.DeleteDiaryMessTask;
import com.sns.service.asynctask.UpdateDiaryTask;
import com.sns.view.DiaryActivity;
import com.sns.view.DiaryMessActivity;
import com.sns.view.PostCommentDialog;

public class DiaryButtonListener implements OnClickListener{

	DiaryMessActivity diaryMessActivity;
	
	public DiaryButtonListener(DiaryMessActivity diaryMessActivity){
		this.diaryMessActivity = diaryMessActivity;
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.dia_btn_update){
System.out.println("按下了修改日志");
			diaryMessActivity.title.setFocusable(true);
			diaryMessActivity.content.setFocusable(true);
		}if(v.getId() == R.id.dia_btn_updateflish){
			try {
				String result = new UpdateDiaryTask(diaryMessActivity).execute(diaryMessActivity.Did,diaryMessActivity.content.getText().toString()).get();
                if(result.equals("OK")){
                	Toast.makeText(diaryMessActivity, "日志修改成功~！", Toast.LENGTH_SHORT).show();
                }else{
                	Toast.makeText(diaryMessActivity, "日志修改失败~！", Toast.LENGTH_SHORT).show();
                }
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}if(v.getId() == R.id.dia_btn_delete){
			try {
				String result = new DeleteDiaryMessTask().execute(diaryMessActivity.Did).get();
				if (result.equals("OK")) {
					Toast.makeText(diaryMessActivity, "日志已删除~", Toast.LENGTH_SHORT).show();
					
					Intent uu = new Intent();
					uu.setClass(diaryMessActivity, DiaryActivity.class);
					uu.putExtra("UID", diaryMessActivity.UID);
					diaryMessActivity.startActivity(uu);
					
				}
	            diaryMessActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
	            diaryMessActivity.finish();
	            
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}if(v.getId() == R.id.dia_btn_comment){
			PostCommentDialog postCommentDialog = new PostCommentDialog(diaryMessActivity);
			postCommentDialog.show();
		}if(v.getId() == R.id.Update_Mess_back){
			Intent uu = new Intent();
			uu.setClass(diaryMessActivity, DiaryActivity.class);
            uu.putExtra("UID", diaryMessActivity.UID);				
            diaryMessActivity.startActivity(uu);
            diaryMessActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            diaryMessActivity.finish();
		}
	}

}
