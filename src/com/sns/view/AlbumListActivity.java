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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.powersns.R;
import com.example.powersns.R.id;
import com.sns.service.asynctask.DeleteAlbumTask;
import com.sns.service.asynctask.DeleteFriendsByIdTask;
import com.sns.service.asynctask.GetAbumListTask;

public class AlbumListActivity extends Activity{
	
	public Button btn_Albumlist_back,btn_Albumlist_add;
	public ListView listview_Albumlist;
	public String UID;
	public List listItem;
	String Album_id;//读取属性
	String Album_name;
	String Album_descript;
	String Album_nestword_addr;
	String AID;
	String FNAME;
	
	public void init(){
		btn_Albumlist_back = (Button)findViewById(R.id.albumlist_back);
		btn_Albumlist_add = (Button)findViewById(R.id.albumlist_add);
		listview_Albumlist = (ListView)findViewById(R.id.listView_albumlist);
		
		btn_Albumlist_back.getBackground().setAlpha(100);
		btn_Albumlist_add.getBackground().setAlpha(100);
		
		btn_Albumlist_back.setOnClickListener(new ButtonListener());
		btn_Albumlist_add.setOnClickListener(new ButtonListener());
		
		Bundle extras = getIntent().getExtras();
		UID = extras.getString("UID");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.albumlist_layout);
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
			if(v.getId()==R.id.albumlist_back){
				Intent uu = new Intent();
				uu.setClass(AlbumListActivity.this, PersonalActivity.class);
                uu.putExtra("UID", UID);				
                AlbumListActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				AlbumListActivity.this.finish();
				
			}if(v.getId()==R.id.albumlist_add){
				Intent uu = new Intent();
				uu.setClass(AlbumListActivity.this, CreateNewAlbumActivity.class);
                uu.putExtra("UID", UID);				
                AlbumListActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
				AlbumListActivity.this.finish();
			}
		}
		
	}
	public void getMess(){
		try {
			SoapObject result = new GetAbumListTask().execute(UID).get();
System.out.println("AlbumListActivity--------->"+result);
			listItem = new ArrayList<Map<String, String>>();
            SoapObject detail = (SoapObject)result.getProperty("getAlbumListResult");
            for(int i = 0;i < detail.getPropertyCount();i++){
            	 SoapObject deta = (SoapObject)detail.getProperty(i);
            	 
            	Album_id = deta.getProperty(0).toString();//读取属性
    			Album_name = deta.getProperty(2).toString();
    			Album_descript = deta.getProperty(3).toString();
    			Album_nestword_addr = deta.getProperty(6).toString();
    			
    			Map<String,String> maps = new HashMap<String, String>();
    			maps.put("Album_name", Album_name);
    			maps.put("Album_id", Album_id);
    			
    			
    			listItem.add(maps);
    			
    			String iidd =maps.get("Album_id");
    			
    			
            }
            
            ListView_FriendsList_Adapter adapter = new ListView_FriendsList_Adapter(
					this,
					listItem, 
					R.layout.albumlist_layout_style, 
				    new String[] {"Album_name","Album_id"},
					new int[] { R.id.albumlist_name,R.id.jia_id});

            listview_Albumlist.setAdapter(adapter);
            
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
public class ListView_FriendsList_Adapter extends BaseAdapter{
		
	    Message msg;
	    MyHandlers myHandlers = new MyHandlers();
	    
		private LayoutInflater mInflater;  
	    private List<Map<String, Object>> list;  
	    private int layoutID;  
	    private String flag[];  
	    private int ItemIDs[];  
	    public ListView_FriendsList_Adapter(Context context, List<Map<String, Object>> list,  
	            int layoutID, String flag[], int ItemIDs[]) {  
	        this.mInflater = LayoutInflater.from(context);  
	        this.list = list;  
	        this.layoutID = layoutID;  
	        this.flag = flag;  
	        this.ItemIDs = ItemIDs;
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
	    	
            final Button btn_delete = ((Button)convertView.findViewById(R.id.albumlist_delete));
            
            btn_delete.setOnClickListener(  
	                new View.OnClickListener() {  
	                    @Override  
	                    public void onClick(View v) {  
	                        try {
	                        	LinearLayout ll = (LinearLayout)btn_delete.getParent();
	                        	LinearLayout linear = (LinearLayout)ll.getParent();
	                        	TextView fid = (TextView)linear.findViewById(id.jia_id);
	                	    	AID = fid.getText().toString();
	                	    	
								String result = new DeleteAlbumTask().execute(AID).get();
								if(result.equals("SUCCESS..")){
									msg = new Message();
									msg.what = 1;
									myHandlers.sendMessage(msg);
								}else{
									msg = new Message();
									msg.what = 2;
									myHandlers.sendMessage(msg);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							}
	                    }  
	                }); 
            
            
            final Button btn_change = ((Button)convertView.findViewById(R.id.albumlist_change));
            
            btn_change.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					LinearLayout ll = (LinearLayout)btn_delete.getParent();
                	LinearLayout linear = (LinearLayout)ll.getParent();
                	TextView fid = (TextView)linear.findViewById(id.jia_id);
                	TextView fname = (TextView)linear.findViewById(id.albumlist_name);
                	FNAME = fname.getText().toString();
        	    	AID = fid.getText().toString();
        	    	
					Intent intent = new Intent();
					intent.setClass(AlbumListActivity.this, PhotoListActivity.class);
					intent.putExtra("UID", UID);
					intent.putExtra("AID", AID);
					intent.putExtra("Album_name", FNAME);
					AlbumListActivity.this.startActivity(intent);
					overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
					AlbumListActivity.this.finish();
				}
			});
	       
	    }  
	    
	    private class MyHandlers extends Handler {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				if (msg.what == 1)// 如果外面传来一个1 ,则我默认要更新界面
				{

					Toast.makeText(AlbumListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
					getMess();
				} else // 访问网络失败
				{
					Toast.makeText(AlbumListActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
				}
			}

		}
	} 
	
}
