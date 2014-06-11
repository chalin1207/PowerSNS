package com.sns.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.example.powersns.R;
import com.sns.service.buttonlistener.LoginButtonListener;
import com.sns.service.buttonlistener.LoginCheckBoxListener;

public class LoginActivity extends Activity{

	
	public Button btn_login,btn_register;
	public EditText et_username,et_password;
	public CheckBox cb_remdmer;
	public SharedPreferences sp;
	public ProgressDialog mSaveDialog = null;
	
	private void init(){
		btn_login = (Button)this.findViewById(R.id.login);
		btn_register = (Button)this.findViewById(R.id.register);
		et_username = (EditText)this.findViewById(R.id.et_username);
		et_password = (EditText)this.findViewById(R.id.et_password);
		cb_remdmer = (CheckBox)this.findViewById(R.id.checkBox1);
		btn_login.setOnClickListener(new LoginButtonListener(this));
		btn_register.setOnClickListener(new LoginButtonListener(this));
		cb_remdmer.setOnCheckedChangeListener(new LoginCheckBoxListener(this));
		
		sp = this.getSharedPreferences("Usermess",LoginActivity.this.MODE_APPEND);
		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		init();
		
		if(sp.getBoolean("isCheck", false)){
			cb_remdmer.setChecked(true);
			et_username.setText(sp.getString("username", null));
			et_password.setText(sp.getString("password", null));
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
