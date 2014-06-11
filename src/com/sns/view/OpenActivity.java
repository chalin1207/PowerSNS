package com.sns.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.powersns.R;

public class OpenActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 3000; //—”≥Ÿ»˝√Î 

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.open_layout);
		
		  new Handler().postDelayed(new Runnable(){ 
			  
		         @Override 
		         public void run() { 
		             Intent mainIntent = new Intent(OpenActivity.this,LoginActivity.class); 
		             OpenActivity.this.startActivity(mainIntent); 
		                 OpenActivity.this.finish(); 
		         } 
		            
		        }, SPLASH_DISPLAY_LENGHT); 
	}

}
