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

            /*���̺���� ���� �� �����ͺ��̽� ����*/
            databaseName_id =   ((Logon)this.getApplication()).getGlobalString();
            
            database_id = openOrCreateDatabase(databaseName_id, Context.MODE_PRIVATE, null);
          //  println("�����ͺ��̽��� ������ϴ�. : " + databaseName);


            /*create login_table*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + login_table + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "id text, "
                        + "pw integer"
                        + ")");
             //   println("���̺��� ��������ϴ�. : " + login_table); // ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
            }

            /*create item_table*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + item_table + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "price integer, "
                        + "state integer"
                        + ")");
             //   println("���̺��� ��������ϴ�. : " + item_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
            }

            /*create buyer_table,, buy_table�� �����ؾ� �ϹǷ� �׻�  buy_table �տ� �� ��*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + buyer_table + "("
                        + "buyer_name text PRIMARY KEY"
                        + ")");
              //  println("���̺��� ��������ϴ�. : " + buyer_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
            }

            /*create seller_table ,, sell_table�� �����ؾ� �ϹǷ� �׻� sell_table �տ� �� ��*/
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + seller_table + "("
                        + "seller_name text PRIMARY KEY"
                        + ")");
              //  println("���̺��� ��������ϴ�. : " + seller_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
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
               // println("���̺��� ��������ϴ�. : " + buy_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
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
              //  println("���̺��� ��������ϴ�. : " + sell_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
            }

            
            //create storage table
            if (database_id != null) {
                database_id.execSQL("CREATE TABLE if not exists " + storage_table + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "ea integer"
                        + ")");
               // println("���̺��� ��������ϴ�. : " + storage_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�

            }



        


        // ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
     //   private void println(String data) {
     //       textView.append(data + "\n");
     //   }
    
    /*
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);*/
   


        // �� �Ǿ�Ƽ��Ƽ���� ����� �� �ִ� TabHost ��ü�� ��´�.    
		TabHost tab_host = getTabHost(); 
        // �� �ǿ� ����� TabSpec ��ü.
		
        // ��ȣ��Ʈ�� TabWidget �� FrameLayout �� ����� ������ �Ѱ��ִ� ������ �Ѵ�.       
		
		tab_host.addTab(tab_host.newTabSpec("tab1")
				.setIndicator("���")
				.setContent(new Intent(this, StockActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		tab_host.addTab(tab_host.newTabSpec("tab2")
				.setIndicator("���")
				.setContent(new Intent(this, RevenueActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		tab_host.addTab(tab_host.newTabSpec("tab3")
				.setIndicator("�԰�")
				.setContent(new Intent(this, PurchaseActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		tab_host.addTab(tab_host.newTabSpec("tab4")
				.setIndicator("���")
				.setContent(new Intent(this, SalesActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				);
		
		
		/*TabHost.TabSpec spec;
        // �� ���� FrameLayout �� ����ϴ� ��Ƽ��Ƽ�� �����ϴ� ��ü    
		
		Intent intent;

        // �ǿ��� ��Ƽ��Ƽ�� ����� �� �ֵ��� ����Ʈ�� �����Ѵ�.       
		intent = new Intent().setClass(this, StockActivity.class);
        // "music" �̶�� �±� ���� ���� TabSpec ��ü�� �����Ѵ�.           
		spec = tab_host.newTabSpec("���");
        // TabSpec ��ü�� TabWidget ��ü�� ����� ���� �̸��� �����Ѵ�.    
		spec.setIndicator("���");
        // TabSpec ��ü�� FrameLayout �� ����� �������� �����Ѵ�.      
		spec.setContent(intent);
        // ��ȣ��Ʈ�� �ش� ������ ���� ���� �߰��Ѵ�.          
		tab_host.addTab(spec);
        
		intent = new Intent().setClass(this, RevenueActivity.class);         
		spec = tab_host.newTabSpec("����"); 
		spec.setIndicator("����");     
		spec.setContent(intent);          
		tab_host.addTab(spec);

		intent = new Intent().setClass(this, PurchaseActivity.class);      
		spec = tab_host.newTabSpec("����");     
		spec.setIndicator("����");      
		spec.setContent(intent);   
		tab_host.addTab(spec);
		  
		intent = new Intent().setClass(this, SalesActivity.class);
		spec = tab_host.newTabSpec("�Ǹ�");  
		spec.setIndicator("�Ǹ�");          
		spec.setContent(intent);           
		tab_host.addTab(spec);*/

        // ù��° ���� ������ ���·� �����Ѵ�.        
		tab_host.setCurrentTab(0);
		
    }
}
