package com.sns.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.ksoap2.serialization.SoapObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powersns.R;
import com.example.powersns.R.id;
import com.sns.service.asynctask.DeleteFriendsByIdTask;
import com.sns.service.asynctask.GetAddFriendsMessTask;
import com.sns.service.asynctask.GetFriendsListTask;

public class FriendsListActivity extends Activity{
	
	public String UID;
	public Button btn_back,btn_add,btn_Fri_delete,btn_mess;
	public ListView listview_FriendsList;
	public String [] _FID;
	public String [] _FNAME;
	List listItem ;
	public TextView tv_inlistview_FID,tv_inlistview_FNAME;
	String FID;
	String [] _Uid;
	int leng;
	public void init(){
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
		
		btn_back = (Button)findViewById(R.id.FriendList_back);
		btn_add = (Button)findViewById(R.id.FriendList_add);
		btn_mess = (Button)findViewById(R.id.Friendlist_messbtn);
		listview_FriendsList = (ListView)findViewById(R.id.listView_Friendslist);
		btn_back.getBackground().setAlpha(100);
		btn_add.getBackground().setAlpha(100);
		btn_back.setOnClickListener(new ButtonListener());
		btn_add.setOnClickListener(new ButtonListener());
		btn_mess.setOnClickListener(new ButtonListener());
		
		SoapObject result;
		try {
			result = new GetAddFriendsMessTask().execute(UID).get();
			SoapObject detail = (SoapObject)result.getProperty("GetFriendRequestListResult");
	        String Uid = detail.getProperty(0).toString();//读取属性
			_Uid=Uid.split("\n");//分离字符串
			leng = _Uid.length;
			if(_Uid[0].equals("anyType{}")){
				leng=0;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		btn_mess.setText("消息("+leng+")");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendlist_layout);
		init();
		getMess();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.FriendList_back){
				Intent uu = new Intent();
				uu.setClass(FriendsListActivity.this, PersonalActivity.class);
                uu.putExtra("UID", UID);				
                FriendsListActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				FriendsListActivity.this.finish();
				
			}if(v.getId() == R.id.FriendList_add){
				Intent uu = new Intent();
				uu.setClass(FriendsListActivity.this, AddFriendActivity.class);
                uu.putExtra("UID", UID);				
                FriendsListActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
				FriendsListActivity.this.finish();
				
			}if(v.getId() == R.id.Friendlist_messbtn){
				Intent uu = new Intent();
				uu.setClass(FriendsListActivity.this, FriendRequestListActivity.class);
                uu.putExtra("UID", UID);				
                FriendsListActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
				FriendsListActivity.this.finish();
				
			}
		}
		
	}
	public void getMess(){
		SoapObject result;
		try {
			result = new GetFriendsListTask().execute(UID).get();
System.out.println("获取好友信息----result------>"+result);
            SoapObject detail = (SoapObject)result.getProperty("GetFriendListResult");
            String Fri_id = detail.getProperty(0).toString();//读取属性
            String Fri_name = detail.getProperty(1).toString();
            
            _FID=Fri_id.split("\n");//分离字符串
			_FNAME=Fri_name.split("\n");
			
			if(!_FID[0].equals("anyType{}")){
				
			listItem = new ArrayList<Map<String, String>>();
			for(int i=0;i < _FID.length;i++)
			{
				Map<String, String> map = new HashMap<String, String>(); 
				map.put("FID", _FID[i]);
				map.put("FNAME",_FNAME[i]);
				
				listItem.add(map);
			}
			
			ListView_FriendsList_Adapter adapter = new ListView_FriendsList_Adapter(
					this,
					listItem, 
					R.layout.friendlist_layout_style, 
				    new String[] {"FID", "FNAME"},
					new int[] { R.id.Fri_list_id,R.id.Fri_list_usename},
					UID);

			listview_FriendsList.setAdapter(adapter);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public class ListView_FriendsList_Adapter extends BaseAdapter{
		
		private String UID;
	    public String FID;
	    Message msg;
	    MyHandlers myHandlers = new MyHandlers();
	    
		private LayoutInflater mInflater;  
	    private List<Map<String, Object>> list;  
	    private int layoutID;  
	    private String flag[];  
	    private int ItemIDs[];  
	    public ListView_FriendsList_Adapter(Context context, List<Map<String, Object>> list,  
	            int layoutID, String flag[], int ItemIDs[],String uid) {  
	        this.mInflater = LayoutInflater.from(context);  
	        this.list = list;  
	        this.layoutID = layoutID;  
	        this.flag = flag;  
	        this.ItemIDs = ItemIDs;
	        this.UID = uid;
	    }  
	    @Override  
	    public int getCount() {  
	        // TODO Auto-generated method stub  
	        return list.size();  
	    }  
	    @Override  
	    public Object getItem(int arg0) {  
	        // TODO Auto-generated method stub  
	        return 0;  
	    }  
	    @Override  
	    public long getItemId(int arg0) {  
	        // TODO Auto-generated method stub  
	        return 0;  
	    }  
	    @Override  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	        convertView = mInflater.inflate(layoutID, null);  
	        for (int i = 0; i < flag.length; i++) {//备注1  
	            if (convertView.findViewById(ItemIDs[i]) instanceof ImageView) {  
	                ImageView iv = (ImageView) convertView.findViewById(ItemIDs[i]);  
	                iv.setBackgroundResource((Integer) list.get(position).get(  
	                        flag[i]));  
	            } else if (convertView.findViewById(ItemIDs[i]) instanceof TextView) {  
	                TextView tv = (TextView) convertView.findViewById(ItemIDs[i]);  
	                tv.setText((String) list.get(position).get(flag[i]));  
	            }else{  
	                //...备注2  
	            }  
	        }  
	        addListener(convertView);  
	        return convertView;  
	    }  
	/** 
	 * 童鞋们只需要将需要设置监听事件的组件写在下面这方法里就可以啦！ 
	 * 别的不需要修改！ 
	 * 备注3 
	 */  
	    public void addListener(View convertView) {
	    	
	    		final Button btn_de = ((Button)convertView.findViewById(R.id.Fri_delete));
	    		btn_de.setOnClickListener(  
	                new View.OnClickListener() {  
	                    @Override  
	                    public void onClick(View v) {  
	                        try {
	                        	LinearLayout ll = (LinearLayout)btn_de.getParent();
	                        	LinearLayout linear = (LinearLayout)ll.getParent();
	                        	TextView fid = (TextView)linear.findViewById(id.Fri_list_id);
	                	    	FID = fid.getText().toString();
	                	    	
								String result = new DeleteFriendsByIdTask().execute(UID,FID).get();
								if(result.equals("OK")){
									msg = new Message();
									msg.what = 1;
									myHandlers.sendMessage(msg);
								}else{
									
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							}
	                    }  
	                });  
	       
	    }  
	    
	    private class MyHandlers extends Handler {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				if (msg.what == 1)// 如果外面传来一个1 ,则我默认要更新界面
				{

					Toast.makeText(FriendsListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
					getMess();
				} else // 访问网络失败
				{
					Toast.makeText(FriendsListActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
				}
			}

		}
	} 

	
}
