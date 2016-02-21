package com.example.project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class StockActivity extends Activity {
	
    String databasename_id;
    SQLiteDatabase database_id;
    String storage_table;
    TextView textView;
    String item_name;
    int item_ea;
    String item_table;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.stock_main);
        item_table = "item_table";
        storage_table = "storage_table";
        databasename_id = ((Logon)this.getApplication()).getGlobalString();
        textView = (TextView)findViewById(R.id.textView);
        try {
            database_id = openOrCreateDatabase(databasename_id, Context.MODE_PRIVATE, null);

          //  println("데이터베이스를 열얼습니다. : " + databasename_id);
        } catch(Exception e) {
            e.printStackTrace();
         
        }
        
        //create storage table
        if (database_id != null) {
            database_id.execSQL("CREATE TABLE if not exists " + storage_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "ea integer"
                    + ")");
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
        Cursor cursor3 = database_id.rawQuery("SELECT name, ea FROM " + storage_table, null);
        
        

        int count = cursor3.getCount(); // 받은 레코드의 갯수

        //println("결과 레코드의 갯수 : " + count);

        for (int i = 0; i < count; i++) {
            cursor3.moveToNext();
            String name = cursor3.getString(0);
            int ea = cursor3.getInt(1);
            
            int price_ea = 0;
            
            Cursor cursor4 = database_id.rawQuery("SELECT price FROM " + item_table + " WHERE name='" + name +"'", null);
            int count4 = cursor4.getCount();
            if(count4 != 0) {
            	cursor4.moveToNext();
            	price_ea = cursor4.getInt(0);
            }
            
            int price_total = price_ea*ea;
            

          
            println(name +"  " + ea + "개 " + "단가 : " + price_ea +"원"+"\n"+"총액 : " +price_total+"\n");
            cursor4.close();
        }
        cursor3.close();
    }
    
    private void println(String data) {
        textView.append(data + "\n");
    }
    
   
}
