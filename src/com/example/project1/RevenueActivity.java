package com.example.project1;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class RevenueActivity extends Activity {
	
	String databaseName;
    SQLiteDatabase database;
    String buy_table;
    String sell_table;
    String buyer_table;
    String seller_table;
    String storage_table;
    String item_name;
    int item_ea;
    int item_price;
    String buyer_name;
    String seller_name;
    ListView listView1;
    ListView listView2;
    /*ListView listView3;
    ListView listView4;
    ListView listView5;
    ListView listView6;
    ListView listView7;
    ListView listView8;
    ListView listView9;
    ListView listView10;*/
    
    ArrayAdapter<String> adapter1;
    
    ArrayAdapter<String> adapter2;
    /*
    ArrayAdapter<String> adapter3;
   
    ArrayAdapter<String> adapter4;
    
    ArrayAdapter<String> adapter5;
    
    ArrayAdapter<CharSequence> adapter6;
   
    ArrayAdapter<CharSequence> adapter7;
    
    ArrayAdapter<CharSequence> adapter8;
    
    ArrayAdapter<CharSequence> adapter9;
    
    ArrayAdapter<CharSequence> adapter10;
    */



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.revenue_main);
       
        

        buy_table = "buy_table";
        sell_table = "sell_table";
        buyer_table = "buyer_table";
        seller_table = "seller_table";
        storage_table = "storage_table";
        

        /*테이블들이 저장 될 데이터베이스 생성*/
        databaseName = ((Logon)this.getApplication()).getGlobalString();
        try {
            database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

           // println("데이터베이스를 열얼습니다. : " + databaseName);
        } catch(Exception e) {
            e.printStackTrace();
        }


        /*create buyer_table,, buy_table이 참조해야 하므로 항상  buy_table 앞에 둘 것*/
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + buyer_table + "("
                    + "buyer_name text PRIMARY KEY"
                    + ")");
         //   println("테이블을 만들었습니다. : " + buyer_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }

        /*create seller_table ,, sell_table이 참조해야 하므로 항상 sell_table 앞에 둘 것*/
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + seller_table + "("
                    + "seller_name text PRIMARY KEY"
                    + ")");
           // println("테이블을 만들었습니다. : " + seller_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }



        // buy_table
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + buy_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "price integer, "
                    + "ea integer, "
                    + "today date default CURRENT_DATE, "
                    + "buyer_name text, "
                    + "FOREIGN KEY(buyer_name) REFERENCES buyer(buyer_name)"
                    + ")");
         //   println("테이블을 만들었습니다. : " + buy_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }

        //sell_table
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + sell_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "price integer, "
                    + "ea integer, "
                    + "today date default CURRENT_DATE, "
                    + "seller_name text, "
                    + "FOREIGN KEY(seller_name) REFERENCES seller(seller_name)"
                    + ")");
           // println("테이블을 만들었습니다. : " + sell_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
        }


        //create storage table
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + storage_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "ea integer"
                    + ")");
        }
        
        Cursor k = database.rawQuery("SELECT _id FROM " + buy_table, null);
        int kk = k.getCount();
        if(kk != 0){
Cursor aa = database.rawQuery("SELECT name, price, ea, today date, buyer_name FROM " + buy_table, null);

        

          	aa.moveToLast();
          ArrayList<String> list1=new ArrayList<String>();
          
          
          String aaaaa1 = aa.getString(0);
          int aaaaa2i = aa.getInt(1);
          String aaaaa2 = String.valueOf(aaaaa2i);
          int aaaaa3i = aa.getInt(2);
          String aaaaa3 = String.valueOf(aaaaa3i);
          String aaaaa4 = aa.getString(3);
          String aaaaa5 = aa.getString(4);
          String aaaaa = aaaaa1;
          aaaaa += "  ";
          aaaaa += aaaaa2;
          aaaaa += "원  ";
          aaaaa += aaaaa3;
          aaaaa += "개  ";
          aaaaa += aaaaa4;
          aaaaa += "  ";
          aaaaa += aaaaa5;
          
         
          list1.add(aaaaa);
              while(aa.moveToPrevious()){
            	  String a1 = aa.getString(0);
                  int a2i = aa.getInt(1);
                  String a2 = String.valueOf(a2i);
                  int a3i = aa.getInt(2);
                  String a3 = String.valueOf(a3i);
                  String a4 = aa.getString(3);
                  String a5 = aa.getString(4);
                  String a = a1;
                  a += "  ";
                  a += a2;
                  a += "원  ";
                  a += a3;
                  a += "개  ";
                  a += a4;
                  a += "  ";
                  a += a5;
          list1.add(a);
              aa.moveToPrevious();
              }
          	
          
           aa.close();
          

           adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
           listView1 = (ListView)findViewById(R.id.listView1);

           listView1.setAdapter(adapter1); 
         
        }
        k.close();
        Cursor l = database.rawQuery("SELECT _id FROM " + sell_table, null);
        int ll = l.getCount();
        if(ll!= 0){
Cursor aa = database.rawQuery("SELECT name, price, ea, today date, seller_name FROM " + sell_table, null);

        

          	aa.moveToLast();
          ArrayList<String> list1=new ArrayList<String>();
          
          
          String aaaaa1 = aa.getString(0);
          int aaaaa2i = aa.getInt(1);
          String aaaaa2 = String.valueOf(aaaaa2i);
          int aaaaa3i = aa.getInt(2);
          String aaaaa3 = String.valueOf(aaaaa3i);
          String aaaaa4 = aa.getString(3);
          String aaaaa5 = aa.getString(4);
          String aaaaa = aaaaa1;
          aaaaa += "  ";
          aaaaa += aaaaa2;
          aaaaa += "원  ";
          aaaaa += aaaaa3;
          aaaaa += "개  ";
          aaaaa += aaaaa4;
          aaaaa += "  ";
          aaaaa += aaaaa5;
          
         
          list1.add(aaaaa);
              while(aa.moveToPrevious()){
            	  String a1 = aa.getString(0);
                  int a2i = aa.getInt(1);
                  String a2 = String.valueOf(a2i);
                  int a3i = aa.getInt(2);
                  String a3 = String.valueOf(a3i);
                  String a4 = aa.getString(3);
                  String a5 = aa.getString(4);
                  String a = a1;
                  a += "  ";
                  a += a2;
                  a += "원  ";
                  a += a3;
                  a += "개  ";
                  a += a4;
                  a += "  ";
                  a += a5;
          list1.add(a);
              aa.moveToPrevious();
              }
          	
          
           aa.close();
          

           adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
           listView2 = (ListView)findViewById(R.id.listView2);

           listView2.setAdapter(adapter2); 
         
        }
        k.close();
        
      
       
        
        Button btnCalllist = (Button) findViewById(R.id.button1);
		btnCalllist.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v)
			{
				Log.i("onClick", "CallSubActivity");
				Intent intentSubActivity = 
						new Intent(RevenueActivity.this, Revenue_listActivity.class);
				startActivity(intentSubActivity);
			}
		
		});
		
	
		
	
	}
    
    
}
    
    
	

