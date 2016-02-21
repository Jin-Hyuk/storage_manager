package com.example.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Purchaseitemlist extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.purchaseitemlist);
	
		
		 Button btnCalldaily = (Button) findViewById(R.id.button3);
			btnCalldaily.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v)
				{
					Log.i("onClick", "CallSubActivity");
					Intent intentSubActivity = 
							new Intent(Purchaseitemlist.this, Purchaseitemnew.class);
					startActivity(intentSubActivity);
				}
			
			});
			Button btnCallmonthly = (Button) findViewById(R.id.button4);
			btnCallmonthly.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v)
				{
					Log.i("onClick", "CallSubActivity");
					Intent intentSubActivity = 
							new Intent(Purchaseitemlist.this, Purchaseitemrevise.class);
					startActivity(intentSubActivity);
				}
			
			});
			Button btnCallitem = (Button) findViewById(R.id.button5);
			btnCallitem.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v)
				{
					Log.i("onClick", "CallSubActivity");
					Intent intentSubActivity = 
							new Intent(Purchaseitemlist.this, Purchaseitemdelete.class);
					startActivity(intentSubActivity);
				}
			
			});
	
	
	
	
	}
}
