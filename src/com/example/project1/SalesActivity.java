package com.example.project1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class SalesActivity extends Activity {
	

    String databasename_id;
    SQLiteDatabase database_id;
    String buy_table;
    String buyer_table;
       String sell_table;
      String seller_table;
      String item_table;
    String storage_table;
int item_ea;
    
    String buy_name;
    String seller_name;

    
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Spinner spinner1;
    int item_price;
    String item_name;
    int sellprice;
    TextView textView1;
    String sellp;

  
    String selected_item;
    public static String item_list1="";
    int item_list2;
   ArrayAdapter<CharSequence> adapter;
   int eaea;
   int price;
  



       @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_main);
 

        
        sell_table = "sell_table";
       
        seller_table = "seller_table";
        buy_table = "buy_table";
        buyer_table = "buyer_table";
        item_table = "item_table";
        storage_table = "storage_table";
        
        
         

        
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
    

        /*테이블들이 저장 될 데이터베이스 생성*/
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
      //create storage table
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + storage_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "ea integer"
                    + ")");
        }
 Cursor ss = database_id.rawQuery("SELECT name FROM " + item_table + " WHERE state = '1' ", null);
        
        

        //  if (s.getString(0) != null){
          	/*for(int i=0; i<=count; i++){
          		s.moveToNext();
          		String a = s.getString(0);
          	
          	startManagingCursor(s);*/
          	
          	
          	
          ArrayList<String> list=new ArrayList<String>();
          while(ss.moveToNext()){
          	String a = ss.getString(0);
          list.add(a);
          	}
           ss.close();
          
          	
          	spinner1 = (Spinner)findViewById(R.id.spinner1);
          	spinner1.setPrompt("품목");
          	adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
          	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinner1.setAdapter(adapter);
          spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
        		@Override
        		public void onItemSelected(AdapterView<?> parentView,
        				View selectedView, int position, long id){
        			item_list1 = String.valueOf(adapter.getItemId(position));
        			item_list2 = Integer.parseInt(item_list1);

        	        
        	          
        		}
        		
        		@Override
        		public void onNothingSelected(AdapterView<?> parentView){}
        		
    		});
          /*
          
          Cursor sss = database_id.rawQuery("SELECT price FROM " + item_table + " WHERE name = '"+item_name+"'", null);
          int nonz = sss.getCount();
          //int price;
          if(nonz != 0){
              sss.moveToNext(); //맨 처음 레코드로 이동

              
              //public static int eaea;
              
				
              		int eaea;
              		int price = sss.getInt(0);
              		if(editText1.getText().toString().length() == 0)
		              {
		                  eaea = 0;
		              }
		              else
		              {
		                  eaea = Integer.parseInt(editText1.getText().toString());
		              }

		              sellprice = eaea*price;

		              textView1 = (TextView) findViewById(R.id.textView1); //textView를 먼저 넣어줘야함
		              textView1.setText(sellprice);
					
		
              

          } else {
              Toast.makeText(getApplicationContext(), "품목이 설정되지 않았습니다.", Toast.LENGTH_LONG).show();
          }
		*/



    }
     
       
       



    // editText로부터 값을 받을 변수
    


   



    public void onButton2Clicked(View v) {
    	
    	if(spinner1.getSelectedItem() == null){
    		Toast.makeText(getApplicationContext(), "물품을 선택하세요", Toast.LENGTH_LONG).show();
    		
    	}
    	else
    	{
    	item_name = (String) spinner1.getSelectedItem();
    	if(editText1.getText().toString().length() == 0)
    	{item_ea = 0;
    	
    	}
    	else{
        item_ea = Integer.parseInt(editText1.getText().toString());
    	}
    	if(editText2.getText().toString().length() == 0)
    	{item_price = 0;
    	
    	}
    	else{    
        item_price = Integer.parseInt(editText2.getText().toString());
    	}
    	if(editText3.getText().toString().length() == 0)
    	{seller_name = "기타";
    	
    	}
    	else{
        seller_name = editText3.getText().toString();
    	}
        // seller_table에 레코드가 없을 경우에만 추가
        Cursor cursor_b = database_id.rawQuery("SELECT seller_name FROM " + seller_table + " WHERE seller_name='"+seller_name+"'", null);
        int count_b = cursor_b.getCount();
        if(count_b ==0) {
            database_id.execSQL("INSERT INTO " + seller_table + "(seller_name) VALUES "
                    + "('" + seller_name + "')");
          //  //println("seller_name 추가"); // 테스트용
        }


        
        ////println("sell_name 추가"); // 테스트용

        //storage 테이블에 해당 아이템이 없는 경우에만 추가
        Cursor cursor1 = database_id.rawQuery("SELECT name, ea FROM " + storage_table + " WHERE name='" + item_name+"'", null);

        int count = cursor1.getCount();
        // storage table에 해당 아이템이 없는 경우 추가
        if(count==0) {
            Toast.makeText(getApplicationContext(), "해당 item의 재고는 없습니다.", Toast.LENGTH_LONG).show();
        }
        // storage table에 이미 해당 아이템이 있는 경우 개수를 수정
        else {
            cursor1.moveToNext();
            int storage_ea = cursor1.getInt(1);
            int ea_bt = storage_ea - item_ea;
            // 재고의 개수가 부족하면 메세지 출력
            if(ea_bt >= 0) {
            	database_id.execSQL("INSERT INTO " + sell_table + "(name, price, ea, seller_name) VALUES "
                        + "('" + item_name + "', " + item_price + ", " + item_ea + ", '" + seller_name +"')");
                database_id.execSQL("UPDATE storage_table SET ea = " + ea_bt + " WHERE name = '" + item_name + "';");
                ////println("storage_table을 갱신했습니다." + item_ea);
                Toast.makeText(getApplicationContext(), "판매 등록.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "해당 item의 재고가 부족합니다.", Toast.LENGTH_LONG).show();
            }
        }
    	}
    }
    

 

    
}
   