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
import com.sns.service.asynctask.GetAddFriendsMessTask;

public class FriendRequestListActivity extends Activity{
	
	public Button btn_back;
	public String UID;
	public ListView listview_Request;
	public String [] _Uid;
	public String [] _Uname;
	public List listItem;
	public String FID;
	public String Fname;
	public void init(){
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
		
		btn_back = (Button)findViewById(R.id.FriendresultList_back);
		btn_back.getBackground().setAlpha(100);
		listview_Request = (ListView)findViewById(R.id.listView_addfriendsmess);
		
		btn_back.setOnClickListener(new ButtonListener());
		listview_Request.setOnItemClickListener(new ListViewListener());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendreaust_layout);
		init();
		getMess();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.FriendresultList_back){
				Intent uu = new Intent();
				uu.setClass(FriendRequestListActivity.this, FriendsListActivity.class);
                uu.putExtra("UID", UID);				
                FriendRequestListActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_down_in,R.anim.push_down_out);
				FriendRequestListActivity.this.finish();
				
			}
		}
		
	}
	
	public void getMess(){
		try {
			SoapObject result = new GetAddFriendsMessTask().execute(UID).get();
			
			SoapObject detail = (SoapObject)result.getProperty("GetFriendRequestListResult");
            String Uid = detail.getProperty(0).toString();//∂¡»° Ù–‘
			String Uname = detail.getProperty(1).toString();
			
			
			_Uid=Uid.split("\n");//∑÷¿Î◊÷∑˚¥Æ
			_Uname=Uname.split("\n");
			if(!_Uid[0].equals("anyType{}")){
				
			listItem = new ArrayList<Map<String, String>>();
			for(int i=0;i < _Uid.length;i++)
			{
				Map<String, String> map = new HashMap<String, String>(); 
				map.put("Uid", _Uid[i]);
				map.put("Uname",_Uname[i]);
				
				listItem.add(map);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(
					this,
					listItem, 
					R.layout.addfriends_layout_style, 
				    new String[] {"Uname", "Uid"},
					new int[] { R.id.addfriend_tv_user,R.id.addfriend_tv_uid});

			listview_Request.setAdapter(adapter);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public class ListViewListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			FID = _Uid[arg2];
			Fname = _Uname[arg2];
			AddFrindsResultDialog addFrindsResultDialog = new AddFrindsResultDialog(FriendRequestListActivity.this);
			addFrindsResultDialog.show();
		}
		
	}
}
