package com.sns.view;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import org.ksoap2.serialization.SoapObject;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.powersns.R;
import com.sns.service.asynctask.GetPersonalTask;
import com.sns.service.asynctask.GetPhotoFaceTask;
import com.sns.service.buttonlistener.PersonalButtonListeners;

public class PersonalActivity extends Activity{
	
	public Button btn_updatemess,btn_updatepass,btn_zhuxiao;
	public ImageButton btn_blog,btn_album,btn_fridend,btn_findendfree,btn_visitor,btn_music;
	public TextView tv_username,tv_xin,tv_adress;
	public ImageView photoface,ic_sex;
	public String [] userinfo;
	private boolean isExit,hasTask;
	public String Mess,AGE,GENDER;
	public Bitmap bbm = null;
	
	
	private void init(){
		btn_updatemess = (Button)findViewById(R.id.update_mess);
		btn_updatepass = (Button)findViewById(R.id.update_pass);
		btn_zhuxiao = (Button)findViewById(R.id.btn_zhuxiao);
		btn_blog = (ImageButton)findViewById(R.id.IB_blog);
		btn_album = (ImageButton)findViewById(R.id.IB_photo);
		btn_fridend = (ImageButton)findViewById(R.id.IB_friend);
		btn_findendfree = (ImageButton)findViewById(R.id.IB_friends_free);
		btn_visitor = (ImageButton)findViewById(R.id.IB_vis);
		btn_music = (ImageButton)findViewById(R.id.IB_music);
		tv_username = (TextView)findViewById(R.id.tv_username);
		tv_xin = (TextView)findViewById(R.id.tv_xin);
		tv_adress = (TextView)findViewById(R.id.tv_adress);
		photoface = (ImageView)findViewById(R.id.photoFace);
		ic_sex = (ImageView)findViewById(R.id.ic_sex);
		
		btn_updatemess.setOnClickListener(new PersonalButtonListeners(this));
		btn_updatepass.setOnClickListener(new PersonalButtonListeners(this));
		btn_blog.setOnClickListener(new PersonalButtonListeners(this));
		btn_album.setOnClickListener(new PersonalButtonListeners(this));
		btn_fridend.setOnClickListener(new PersonalButtonListeners(this));
		btn_findendfree.setOnClickListener(new PersonalButtonListeners(this));
		btn_visitor.setOnClickListener(new PersonalButtonListeners(this));
		btn_music.setOnClickListener(new PersonalButtonListeners(this));
		btn_zhuxiao.setOnClickListener(new PersonalButtonListeners(this));
		btn_zhuxiao.getBackground().setAlpha(100);

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_layout);
		init();
		getMess();
		setTextView();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        Timer exitTimer = new Timer();  
        TimerTask task = new TimerTask(){  
            @Override  
            public void run(){  
                isExit = false;  
                hasTask = false;  
            }  
        };  
        if(keyCode == KeyEvent.KEYCODE_BACK){  
            if(isExit==false){  
                isExit = true;  
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
                if(!hasTask){  
                    hasTask=true;  
                    exitTimer.schedule(task, 2000);  
                }  
            }else{  
                finish();  
                System.exit(0); 
                
            }  
        }  
        return false;  
    }
	
	public void getMess(){
		
		Bundle extras = getIntent().getExtras();
		Mess = extras.getString("UID");
		
System.out.println("000000000---->"+Mess);
        try {
			SoapObject some = new GetPersonalTask().execute(Mess).get();
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!---->"+some);
			SoapObject detail = (SoapObject)some.getProperty("GetUserInfoByIdResult");
			userinfo = new String[3];
			userinfo[0] = detail.getProperty(1).toString();
			userinfo[1] = detail.getProperty(3).toString();
			userinfo[2] = detail.getProperty(6).toString();
			AGE = detail.getProperty(4).toString();
			GENDER = detail.getProperty(5).toString();
			
			bbm = new GetPhotoFaceTask().execute(userinfo[2]).get();
			photoface.setImageBitmap(bbm);
			
			if(GENDER.equals("男")){
			ic_sex.setImageResource(R.drawable.ic_male);
		}if(GENDER.equals("女")){
			ic_sex.setImageResource(R.drawable.ic_female);
		}
			
System.out.println("PersonalActivity----->"+bbm);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
	}
	
	public void setTextView(){
		tv_username.setText(userinfo[0]);
		tv_xin.setText(userinfo[1]);
	}
	
}
