package com.sns.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.powersns.R;
import com.sns.service.asynctask.GetDiaryListTask;

public class DiaryActivity extends Activity{
	
	ListView listView_Mess;
	String UID;
	List listItem ;
	public Button back,write;
	public String[] _Did;
	public String[] _Dtitle;
	public String[] _Ddate;
	public String[] _DContent;
	
	public void init(){
		listView_Mess = (ListView)findViewById(R.id.listView_Mess);
		back = (Button)findViewById(R.id.btn_back);
		write = (Button)findViewById(R.id.btn_write);
		back.setText("back");
		back.getBackground().setAlpha(100);
		write.getBackground().setAlpha(100);
		back.setOnClickListener(new TitleButtonListener());
		write.setOnClickListener(new TitleButtonListener());
		listView_Mess.setOnItemClickListener(new ListViewListener());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diaryactivity);
		init();
		getDiaryMess();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	public void getDiaryMess(){
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
		SoapObject some;
		try {
			some = new GetDiaryListTask().execute(UID).get();
System.out.println("DiaryActivity------some------->"+some);
            SoapObject detail = (SoapObject)some.getProperty("GetDiaryListResult");
            String Did = detail.getProperty(0).toString();//∂¡»° Ù–‘
			String Dtitle = detail.getProperty(1).toString();
			String DDate = detail.getProperty(2).toString();
			String DContent=detail.getProperty(3).toString();
			
			_Did=Did.split("\n");//∑÷¿Î◊÷∑˚¥Æ
			_Dtitle=Dtitle.split("\n");
			_Ddate=DDate.split("\n");
			_DContent=DContent.split("\n");
			
			listItem = new ArrayList<Map<String, String>>();
			for(int i=0;i < _Did.length;i++)
			{
				Map<String, String> map = new HashMap<String, String>(); 
				map.put("Did", _Did[i]);
				map.put("Dtitle",_Dtitle[i]);
				map.put("DDate",_Ddate[i]);	
				map.put("DContent",_DContent[i]);
				listItem.add(map);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(
					this,
					listItem, 
					R.layout.diary_style_layout, 
				    new String[] {"Dtitle", "DDate", "DContent" ,"Did"},
					new int[] { R.id.tv_title,R.id.tv_date, R.id.tv_content ,R.id.TV_THIN_DID});

			listView_Mess.setAdapter(adapter);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
	
	private class TitleButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_back){
				Intent uu = new Intent();
				uu.setClass(DiaryActivity.this, PersonalActivity.class);
                uu.putExtra("UID", UID);				
                DiaryActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
//				DiaryActivity.this.finish();
				
			}if(v.getId() == R.id.btn_write){
				Intent uu = new Intent();
				uu.setClass(DiaryActivity.this, WriteDiaryActivity.class);
                uu.putExtra("UID", UID);				
                DiaryActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
//				DiaryActivity.this.finish();
				
			}
		}
		
	}
	
	private class ListViewListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent();
			intent.setClass(DiaryActivity.this, DiaryMessActivity.class);
			intent.putExtra("Did", _Did[arg2]);
			intent.putExtra("Dtitle", _Dtitle[arg2]);
			intent.putExtra("DDate", _Ddate[arg2]);
			intent.putExtra("DContent", _DContent[arg2]);
			intent.putExtra("UID",UID);
            DiaryActivity.this.startActivity(intent); 
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            DiaryActivity.this.finish();
            
		}
		
	}
	
}
