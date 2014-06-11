package com.sns.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.example.powersns.R;
import com.sns.service.buttonlistener.RegisterButtonListener;

public class RegisterActivity extends Activity{
	
	public Button btn_reg,btn_cancel;
	public EditText et_usernum,et_pass,et_passagain,et_username;
	
	private void init(){
		btn_reg = (Button)this.findViewById(R.id.btn_reg);
		btn_cancel = (Button)this.findViewById(R.id.btn_cancel);
		et_usernum = (EditText)this.findViewById(R.id.et_usernum);
		et_pass = (EditText)this.findViewById(R.id.et_pass);
		et_passagain = (EditText)this.findViewById(R.id.et_passagain);
		et_username = (EditText)this.findViewById(R.id.et_username);
		
		btn_reg.setOnClickListener(new RegisterButtonListener(this));
		btn_cancel.setOnClickListener(new RegisterButtonListener(this));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		init();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
