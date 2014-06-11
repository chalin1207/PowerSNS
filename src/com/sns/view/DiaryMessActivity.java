package com.sns.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.powersns.R;
import com.sns.service.asynctask.GetCommentListTask;
import com.sns.service.buttonlistener.DiaryButtonListener;

public class DiaryMessActivity extends Activity{
	
	public EditText title,content;
	public TextView textView_date;
	public Button update_diary,update_flish,delete_diary,comment_diary,update_Mess_back;
	public ListView ListView_Comment;
	public String Did,Dtitle,DDate,DContent;
	public String UID;
	public List listItem;
	public SimpleAdapter adapter;
	
	public void init(){
		title = (EditText)findViewById(R.id.Update_Diary_title);
		content = (EditText)findViewById(R.id.Update_Diary_content);
		textView_date = (TextView)findViewById(R.id.update_diary_date);
		update_diary = (Button)findViewById(R.id.dia_btn_update);
		update_flish = (Button)findViewById(R.id.dia_btn_updateflish);
		delete_diary = (Button)findViewById(R.id.dia_btn_delete);
		comment_diary = (Button)findViewById(R.id.dia_btn_comment);
		update_Mess_back = (Button)findViewById(R.id.Update_Mess_back);
		ListView_Comment = (ListView)findViewById(R.id.ListView_Comment);
		update_Mess_back.getBackground().setAlpha(100);
		
		Bundle extras = getIntent().getExtras();
		Did = extras.getString("Did");
		Dtitle = extras.getString("Dtitle");
		DDate = extras.getString("DDate");
		DContent = extras.getString("DContent");
		UID = extras.getString("UID");
		
		
		update_diary.setOnClickListener(new DiaryButtonListener(this));
		update_flish.setOnClickListener(new DiaryButtonListener(this));
		delete_diary.setOnClickListener(new DiaryButtonListener(this));
		comment_diary.setOnClickListener(new DiaryButtonListener(this));
		update_Mess_back.setOnClickListener(new DiaryButtonListener(this));
	}
	private void getMess(){
		title.setText(Dtitle);
		textView_date.setText(DDate);
		content.setText(DContent);
		
		title.setFocusable(false);
//		content.setFocusable(false);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_about_layout);
		init();
		getMess();
		getComment();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void getComment(){
		try {
			SoapObject comment = new GetCommentListTask().execute(Did).get();
System.out.println("DiaryMessActivity------comment------->"+comment);
            listItem = new ArrayList<Map<String, String>>();
            SoapObject detail = (SoapObject)comment.getProperty("GetCommentListResult");
            for(int i = 0;i < detail.getPropertyCount();i++){
            	 SoapObject deta = (SoapObject)detail.getProperty(i);
            	 
            	String CDate = deta.getProperty(2).toString();//¶ÁÈ¡ÊôÐÔ
    			String ComContent = deta.getProperty(3).toString();
    			String FName = deta.getProperty(4).toString();
    			
    			Map<String,String> maps = new HashMap<String, String>();
    			maps.put("CDate", CDate);
    			maps.put("ComContent", ComContent);
    			maps.put("FName", FName);
    			
    			listItem.add(maps);
            }
            
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		adapter = new SimpleAdapter(
				this,
				listItem, 
				R.layout.comment_style, 
			    new String[] {"FName", "ComContent", "CDate" },
				new int[] { R.id.CFri_name,R.id.CFri_content, R.id.CFri_date});

		ListView_Comment.setAdapter(adapter);
	}

	
}
