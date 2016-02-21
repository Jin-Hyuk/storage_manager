package com.example.project1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Revenue_daily extends Activity {
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
    TextView textView1;
    TextView textView2;
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
         
        setContentView(R.layout.revenue_daily);
       
        

        buy_table = "buy_table";
        sell_table = "sell_table";
        buyer_table = "buyer_table";
        seller_table = "seller_table";
        storage_table = "storage_table";
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        
        

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
        int money_in=0;
        int money_out=0;
        int money_result=0;
      //where에서 오늘을 어떻게 검색하지??
        Cursor cursor_in = database.rawQuery("SELECT name, ea, price, today FROM  buy_table WHERE today= date('now')", null);

        int count_in = cursor_in.getCount(); // 받은 레코드의 갯수

        println("매입  " + count_in + " 건");

        for (int i = 0; i < count_in; i++) {
            cursor_in.moveToNext();
            String name = cursor_in.getString(0);
            int ea = cursor_in.getInt(1);
            int price = cursor_in.getInt(2);
            String date = cursor_in.getString(3);

            println(name +"  " + price + "원  " + ea + "개  " + date);
            money_out += price;
        }
        cursor_in.close();


        Cursor cursor_out = database.rawQuery("SELECT name, ea, price, today FROM  sell_table WHERE today= date('now')", null);

        int count_out = cursor_out.getCount(); // 받은 레코드의 갯수

        println("\n" +"매출  " + count_out + " 건");

        for (int i = 0; i < count_out; i++) {
            cursor_out.moveToNext();
            String name = cursor_out.getString(0);
            int ea = cursor_out.getInt(1);
            int price = cursor_out.getInt(2);
            String date = cursor_out.getString(3);

            println(name +"  " + price + "원  " + ea + "개  " + date);
            money_in += price;
        }
        cursor_out.close();

        println("\n" +"오늘의 매입금액" +"  " + money_out+ "원  ");

        println("오늘의 매출금액" + "  " +money_in+ "원  ");
        money_result = (money_in - money_out);
        

        println("오늘의 손익" + "  " +money_result+ "원  ");
    }
    private void println(String data) {
        textView1.append(data + "\n");
        
        
    }
    
    public void onButton1Clicked(View v) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String txt = null;
        
         
        switch (v.getId()) {
        case R.id.button1:
            txt = ((TextView) findViewById(R.id.textView1)).getText().toString();
            clipboardManager.setText(txt);
            break;
        }
    }
   

    
}

        
      
       