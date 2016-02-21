package com.example.project1;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
//import android.widget.TextView;
import android.content.Context;

public class MainActivity extends TabActivity {

	
    
    
    
    String databaseName_id;
    SQLiteDatabase database_id;
   // TextView textView;
    String login_table;
    String item_table;
    String buyer_table;
    String seller_table;
    String buy_table;
    String sell_table;
    String storage_table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    

            //textView = (TextView) findViewById(R.id.textView);
            login_table = "login_table";
            item_table = "item_table";
            buyer_table = "buyer_table";
            seller_table = "seller_table";
            buy_table = "buy_table";
            sell_table = "sell_table";
            storage_table = "storage_table";

            /*테이블들이 저장 될 데이터베이스 생성*/
            databaseName_id =   ((Logon)this.getApplication()).getGlobalString();
            
            database_id = openOrCreateDatabase(databaseName_id, Context.MODE_PRIVATE, null);
          //  println("데이터베이스를 열얼습니다. : " + databaseName);


            /*create login_table*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + login_table + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "id text, "
                        + "pw integer"
                        + ")");
             //   println("테이블을 만들었습니다. : " + login_table); // 제대로 작동하나 확인용이라 지우셔도 돼요
            }

            /*create item_table*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + item_table + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "price integer, "
                        + "state integer"
                        + ")");
             //   println("테이블을 만들었습니다. : " + item_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
            }

            /*create buyer_table,, buy_table이 참조해야 하므로 항상  buy_table 앞에 둘 것*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + buyer_table + "("
                        + "buyer_name text PRIMARY KEY"
                        + ")");
              //  println("테이블을 만들었습니다. : " + buyer_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
            }

            /*create seller_table ,, sell_table이 참조해야 하므로 항상 sell_table 앞에 둘 것*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + seller_table + "("
                        + "seller_name text PRIMARY KEY"
                        + ")");
              //  println("테이블을 만들었습니다. : " + seller_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
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
               // println("테이블을 만들었습니다. : " + buy_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
            }




            // sell_table
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
              //  println("테이블을 만들었습니다. : " + sell_table);// 제대로 작동하나 확인용이라 지우셔도 돼요
            }

            
            //create storage table
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + storage_table + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "ea integer"
                        + ")");
               // println("테이블을 만들었습니다. : " + storage_table);// 제대로 작동하나 확인용이라 지우셔도 돼요

            }



        


        // 제대로 작동하나 확인용이라 지우셔도 돼요
     //   private void println(String data) {
     //       textView.append(data + "\n");
     //   }
    
    /*
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);*/
   


        // 이 탭액티비티에서 사용할 수 있는 TabHost 객체를 얻는다.    
		TabHost tab_host = getTabHost(); 
        // 각 탭에 사용할 TabSpec 객체.
		
        // 탭호스트에 TabWidget 과 FrameLayout 이 사용할 정보를 넘겨주는 역할을 한다.       
		
		tab_host.addTab(tab_host.newTabSpec("tab1")
				.setIndicator("재고")
				.setContent(new Intent(this, StockActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		tab_host.addTab(tab_host.newTabSpec("tab2")
				.setIndicator("장부")
				.setContent(new Intent(this, RevenueActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		tab_host.addTab(tab_host.newTabSpec("tab3")
				.setIndicator("입고")
				.setContent(new Intent(this, PurchaseActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		tab_host.addTab(tab_host.newTabSpec("tab4")
				.setIndicator("출고")
				.setContent(new Intent(this, SalesActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		
		
		/*TabHost.TabSpec spec;
        // 각 탭의 FrameLayout 이 사용하는 액티비티를 구성하는 객체    
		
		Intent intent;

        // 탭에서 액티비티를 사용할 수 있도록 인텐트를 생성한다.       
		intent = new Intent().setClass(this, StockActivity.class);
        // "music" 이라는 태그 값을 가진 TabSpec 객체를 생성한다.           
		spec = tab_host.newTabSpec("재고");
        // TabSpec 객체에 TabWidget 객체가 출력할 탭의 이름을 설정한다.    
		spec.setIndicator("재고");
        // TabSpec 객체에 FrameLayout 이 출력할 페이지를 설정한다.      
		spec.setContent(intent);
        // 탭호스트에 해당 정보를 가진 탭을 추가한다.          
		tab_host.addTab(spec);
        
		intent = new Intent().setClass(this, RevenueActivity.class);         
		spec = tab_host.newTabSpec("매출"); 
		spec.setIndicator("매출");     
		spec.setContent(intent);          
		tab_host.addTab(spec);

		intent = new Intent().setClass(this, PurchaseActivity.class);      
		spec = tab_host.newTabSpec("매입");     
		spec.setIndicator("매입");      
		spec.setContent(intent);   
		tab_host.addTab(spec);
		  
		intent = new Intent().setClass(this, SalesActivity.class);
		spec = tab_host.newTabSpec("판매");  
		spec.setIndicator("판매");          
		spec.setContent(intent);           
		tab_host.addTab(spec);*/

        // 첫번째 탭을 선택한 상태로 지정한다.        
		tab_host.setCurrentTab(0);
		
    }
}
