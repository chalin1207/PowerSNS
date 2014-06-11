package com.sns.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powersns.R;

public class UpdatePasswordActivity extends Activity{
	
	public TextView tv_name;
	public Button btn_submit,btn_cancel;
	public String UID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_password_layout);
		btn_submit = (Button)findViewById(R.id.b_submit);
		btn_cancel = (Button)findViewById(R.id.b_cancel);
		
		tv_name = (TextView)this.findViewById(R.id.name);
		
		btn_submit.setOnClickListener(new ButtonListener());
		btn_cancel.setOnClickListener(new ButtonListener());
		
		Bundle extras = getIntent().getExtras();
		String Mess = extras.getString("username");
		UID = extras.getString("UID");
		tv_name.setText(Mess);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.b_submit){
				Toast.makeText(UpdatePasswordActivity.this, "该功能还没完善",Toast.LENGTH_SHORT).show();
			}if(v.getId()==R.id.b_cancel){
				
				Intent uu = new Intent();
				uu.setClass(UpdatePasswordActivity.this, PersonalActivity.class);
                uu.putExtra("UID", UID);				
				UpdatePasswordActivity.this.startActivity(uu);
				overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
				UpdatePasswordActivity.this.finish();
			}
		}
		
	}
}
