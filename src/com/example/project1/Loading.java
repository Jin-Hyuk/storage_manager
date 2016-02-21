package com.example.project1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class Loading extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.loading);
         
	        Handler hd = new Handler();
	        hd.postDelayed(new Runnable() {
	 
	            @Override
	            public void run() {
	                finish();   //   가면서 바로튕 
	            }
	        }, 3000);		
	}


}