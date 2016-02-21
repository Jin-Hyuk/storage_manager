package com.example.project1;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class PurchaseActivity extends Activity {
   

    String databasename_id;
    SQLiteDatabase database_id;
    String buy_table;
    String buyer_table;
    String sell_table;
    String seller_table;
    String item_table;
    String storage_table;
    int item_ea;
    String buyer_name;
    String seller_name;
        Spinner spinner2;
    String item_name;
    int item_price;
    String selected_item2;
    public static String item_list2="";
   ArrayAdapter<CharSequence> adapter2;
    EditText editText21;
    EditText editText22;
    EditText editText23;
    
    
   
	
	@Override
    public void onCreate(Bundle savedInstanceState){
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_main);
            
	
    	
        buy_table = "buy_table";
        buyer_table = "buyer_table";
        sell_table = "sell_table";
       
        seller_table = "seller_table";
        storage_table = "storage_table";
        item_table = "item_table";
      // spinner2 = (Spinner)findViewById(R.id.spinner2);
        editText21 = (EditText)findViewById(R.id.editText21);
        editText22 = (EditText)findViewById(R.id.editText22);
        editText23 = (EditText)findViewById(R.id.editText23);


        //테이블들이 저장 될 데이터베이스 생성
        databasename_id = ((Logon)this.getApplication()).getGlobalString();
        try {
            database_id = openOrCreateDatabase(databasename_id, Context.MODE_PRIVATE, null);

           // //println("데이터베이스를 열얼습니다. : " + databaseName);
      } catch(Exception e) {
            e.printStackTrace();
            }


        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + buyer_table + "("
                    + "buyer_name text PRIMARY KEY"
                    + ")");
            //println("테이블을 만들었습니다. : " + buyer_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }

        //create seller_table ,, sell_table이 참조해야 하므로 항상 sell_table 앞에 둘 것
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + seller_table + "("
                    + "seller_name text PRIMARY KEY"
                    + ")");
            //println("테이블을 만들었습니다. : " + seller_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }



        // buy_table
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + buy_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "price integer, "
                    + "ea integer, "
                    + "today date default CURRENT_DATE, "
                    + "buyer_name text, "
                    + "FOREIGN KEY(buyer_name) REFERENCES buyer_table(buyer_name)"
                    + ")");
            //println("테이블을 만들었습니다. : " + buy_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }

        //sell_table
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + sell_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "price integer, "
                    + "ea integer, "
                    + "today date default CURRENT_DATE, "
                    + "seller_name text, "
                    + "FOREIGN KEY(seller_name) REFERENCES seller_table(seller_name)"
                    + ")");
            //println("테이블을 만들었습니다. : " + sell_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }
        //create item_table
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + item_table + "("
            		   + "_id integer PRIMARY KEY autoincrement, "
	                    + "name text, "
                    + "price integer, "
                    + "state integer"
                    + ")");
         //   //println("테이블을 만들었습니다. : " + item_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }
        Cursor s = database_id.rawQuery("SELECT name FROM " + item_table + " WHERE state = '1' ", null);
        
        

        //  if (s.getString(0) != null){
         // 	for(int i=0; i<=count; i++){
          	//	s.moveToNext();
          //		String a = s.getString(0);
          //	
          //	startManagingCursor(s);
          	
          	
          	
          ArrayList<String> list2=new ArrayList<String>();
          while(s.moveToNext()){
          	String b = s.getString(0);
          list2.add(b);
          	}
           s.close();
          
          	
          	spinner2 = (Spinner)findViewById(R.id.spinner2);
          	spinner2.setPrompt("품목");
          	adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list2);
          	adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinner2.setAdapter(adapter2);
          //String str = (String) spinner2.getSelectedItem();
        //  selected_item2 = str;
         
          
          
        		  
          	
          	
          
          
          	
         spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
      		@Override
      		public void onItemSelected(AdapterView<?> parentView2,
      				View selectedView2, int position2, long id2){
      			item_list2 = String.valueOf(adapter2.getItemId(position2));
      			}
      		@Override
      		public void onNothingSelected(AdapterView<?> parentView2){}
      		
  		});
     


        //create storage table
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + storage_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "ea integer"
                    + ")");
        }
        //버튼 리스트로 이동부분
        Button btnCalllist = (Button) findViewById(R.id.button1);
		btnCalllist.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v)
			{
				Log.i("onClick", "CallSubActivity");
				Intent intentSubActivity = 
						new Intent(PurchaseActivity.this, Purchaseitemlist.class);
				startActivity(intentSubActivity);
			}
		
		});
		 
      
        

    }



    // editText로부터 값을 받을 변수
	
    
    
    
   
    // 버튼을 누르면 editText에서 값을 받아서 buyer_table과 buy_table에 값을 추가
    public void onButton1Clicked(View v) {
    	if(spinner2.getSelectedItem() == null){
    		Toast.makeText(getApplicationContext(), "물품을 선택하세요", Toast.LENGTH_LONG).show();
    		
    	}
    	else
    	{
    	item_name = (String) spinner2.getSelectedItem();
    	//item_name = selected_item2;
    	if(editText21.getText().toString().length() == 0)
    	{item_ea = 0;
    	
    	}
    	else{
        item_ea = Integer.parseInt(editText21.getText().toString());
    	}
    	if(editText22.getText().toString().length() == 0)
    	{item_price = 0;
    	
    	}
    	else{    
        item_price = Integer.parseInt(editText22.getText().toString());
    	}
    	if(editText23.getText().toString().length() == 0)
    	{buyer_name = "기타";
    	
    	}
    	else{
        buyer_name = editText23.getText().toString();
    	}
       //if(editText21.getText().toString() == null || editText22.getText().toString() == null || editText23.getText().toString().length() == 0)
        ///{ Toast.makeText(getApplicationContext(), "정보를 모두 입력하세요", Toast.LENGTH_LONG).show();}
        //else{
        
    	
    	
    	

        Cursor cursor_a = database_id.rawQuery("SELECT buyer_name FROM " + buyer_table + " WHERE buyer_name='"+buyer_name+"'", null);

        int count_a = cursor_a.getCount();


        // buyer_table에 레코드가 없는 경우에만 추가
        if(count_a ==0) {

            database_id.execSQL("INSERT INTO " + buyer_table + "(buyer_name) VALUES "
                    + "('" + buyer_name + "')");
          //  //println("buyer_name 추가"); // 테스트용
        }
    	

        // buy_table에 레코드 추가
        database_id.execSQL("INSERT INTO " + buy_table + "(name, price, ea, buyer_name) VALUES "
                + "('" + item_name + "', " + item_price + ", " + item_ea + ", '" + buyer_name + "')");
       // //println("buy_name 추가"); // 테스트용


        //storage 테이블에 해당 아이템이 없는 경우에만 추가
        Cursor cursor2 = database_id.rawQuery("SELECT name, ea FROM " + storage_table + " WHERE name='" + item_name+"'", null);
    	
        

        int count2 = cursor2.getCount();
        // storage table에 해당 아이템이 없는 경우 추가
       
        	
        		if(count2==0) {
        			database_id.execSQL("INSERT INTO " + storage_table + "(name, ea) VALUES "
                    + "('" + item_name + "', " + item_ea + ")");
        			Toast.makeText(getApplicationContext(), "매입 등록.", Toast.LENGTH_LONG).show();
        		}
        		else {
        			cursor2.moveToNext();
        			int storage_ea = cursor2.getInt(1);
        			item_ea = storage_ea + item_ea;
        			database_id.execSQL("UPDATE storage_table SET ea = " + item_ea + " WHERE name = '"+ item_name +"';");
        			Toast.makeText(getApplicationContext(), "매입 추가.", Toast.LENGTH_LONG).show();
        		}
        	
        }
    }
    
}

