package com.sns.view;

import java.util.concurrent.ExecutionException;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.powersns.R;
import com.sns.service.asynctask.PostCommentTask;

public class PostCommentDialog extends Dialog{

	DiaryMessActivity diaryMessActivity;
	public Button btn_submit,btn_cancel;
	public EditText et_Fid,et_content;
	
	public PostCommentDialog(Context context) {
		super(context);
		diaryMessActivity = (DiaryMessActivity)context;
	}

	public void init(){
		btn_submit = (Button)this.findViewById(R.id.dialog_submit);
		btn_cancel = (Button)this.findViewById(R.id.dialog_cancel);
		et_Fid = (EditText)this.findViewById(R.id.dialog_Fid);
		et_content = (EditText)this.findViewById(R.id.dialog_content);
		btn_submit.setOnClickListener(new DialogButtonListener());
		btn_cancel.setOnClickListener(new DialogButtonListener());
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.postcommentdialog);
		init();
		this.setTitle("添加评论");
	}
	
	private class DialogButtonListener implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.dialog_submit){
				try {
					String result = new PostCommentTask(PostCommentDialog.this).execute(diaryMessActivity.Did,et_content.getText().toString(),et_Fid.getText().toString()).get();
System.out.println("提交评论-----result------>"+result);					
					if(result.equals("OK")){
						diaryMessActivity.getComment();
						PostCommentDialog.this.dismiss();
						Toast.makeText(diaryMessActivity, "添加评论成功~", Toast.LENGTH_SHORT).show();
					}else{
						PostCommentDialog.this.dismiss();
						Toast.makeText(diaryMessActivity, "添加评论失败~", Toast.LENGTH_SHORT).show();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}if(v.getId() == R.id.dialog_cancel){
				PostCommentDialog.this.dismiss();
			}
		}

		
		
	}
	
	
}
