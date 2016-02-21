package com.example.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Revenue_listActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.revenue_list);
		
		 Button btnCalldaily = (Button) findViewById(R.id.button3);
			btnCalldaily.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v)
				{
					Log.i("onClick", "CallSubActivity");
					Intent intentSubActivity = 
							new Intent(Revenue_listActivity.this, Revenue_daily.class);
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
							new Intent(Revenue_listActivity.this, Revenue_monthly.class);
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
							new Intent(Revenue_listActivity.this, Revenue_item.class);
					startActivity(intentSubActivity);
				}
			
			});
	
	
	
	
	}
	
}
