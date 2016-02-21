package com.example.project1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Revenue_item extends Activity {

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
    Spinner spinner1;
    String selected_month;
    String selectedm;
    String reyear;
    ArrayAdapter<String> adapter1;
    
    ArrayAdapter<String> adapter2;
    
    
   
    ArrayAdapter<String> adapter3;
   /* 
    ArrayAdapter<String> adapter5;
    
    ArrayAdapter<CharSequence> adapter6;
   
    ArrayAdapter<CharSequence> adapter7;
    
    ArrayAdapter<CharSequence> adapter8;
    
    ArrayAdapter<CharSequence> adapter9;
    
    ArrayAdapter<CharSequence> adapter10;
    */
    

    
 
    DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date nowDate = new Date();
    String tempDate = sdFormat.format(nowDate);
    String sub2 = tempDate.substring(0,8);
    String month = tempDate.substring(5,7);
    String year = tempDate.substring(0,4);
    int yearint = Integer.parseInt(year);
    int monthint = Integer.parseInt(month);
    int countm = 0;
    String a;
    String item_table;
    	 
    	 
    
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.revenue_item);
       
        
     
        item_table = "item_table";
        buy_table = "buy_table";
        sell_table = "sell_table";
        buyer_table = "buyer_table";
        seller_table = "seller_table";
        storage_table = "storage_table";
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        
        

        /*���̺���� ���� �� �����ͺ��̽� ����*/
        databaseName = ((Logon)this.getApplication()).getGlobalString();
        try {
            database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

           // println("�����ͺ��̽��� ������ϴ�. : " + databaseName);
        } catch(Exception e) {
            e.printStackTrace();
        }
      //create item_table
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + item_table + "("
            		   + "_id integer PRIMARY KEY autoincrement, "
	                    + "name text, "
                    + "price integer, "
                    + "state integer"
                    + ")");
         //   //println("���̺��� ��������ϴ�. : " + item_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
        }


        /*create buyer_table,, buy_table�� �����ؾ� �ϹǷ� �׻�  buy_table �տ� �� ��*/
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + buyer_table + "("
                    + "buyer_name text PRIMARY KEY"
                    + ")");
         //   println("���̺��� ��������ϴ�. : " + buyer_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
        }

        /*create seller_table ,, sell_table�� �����ؾ� �ϹǷ� �׻� sell_table �տ� �� ��*/
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + seller_table + "("
                    + "seller_name text PRIMARY KEY"
                    + ")");
           // println("���̺��� ��������ϴ�. : " + seller_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
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
         //   println("���̺��� ��������ϴ�. : " + buy_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
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
           // println("���̺��� ��������ϴ�. : " + sell_table);// ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
        }


        //create storage table
        if (database != null) {
            database.execSQL("CREATE TABLE if not exists " + storage_table + "("
                    + "_id integer PRIMARY KEY autoincrement, "
                    + "name text, "
                    + "ea integer"
                    + ")");
        }
        
        

  Cursor s = database.rawQuery("SELECT name FROM " + item_table + " WHERE state = '1' ", null);
        
        

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
          
          	
          	spinner1 = (Spinner)findViewById(R.id.spinner1);
          	spinner1.setPrompt("ǰ��");
          	adapter3=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list2);
          	adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinner1.setAdapter(adapter3);
          //String str = (String) spinner2.getSelectedItem();
        //  selected_item2 = str;
         
          
          
        		  
          	
          	
          
           
          	
         spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
      		@Override
      		public void onItemSelected(AdapterView<?> parentView2,
      				View selectedView2, int position2, long id2){
      			a = String.valueOf(adapter3.getItemId(position2));
      			}
      		@Override
      		public void onNothingSelected(AdapterView<?> parentView2){}
      		
  		});
      	
      	
      
      
      
    }
  
     public void onButton2Clicked(View v) {
     
     selectedm = (String) spinner1.getSelectedItem();
     
     
     
     textView1.setText(null);
        
       
     
        
     int money_in=0;
     int money_out=0;
     int money_result=0;
      //where���� ������ ��� �˻�����??
        
        Cursor cursor_in = database.rawQuery("SELECT name, ea, price, today FROM  buy_table WHERE name = '"+selectedm+"'", null);

        int count_in = cursor_in.getCount(); // ���� ���ڵ��� ����

        println("����  " + count_in + " ��");

        for (int i = 0; i < count_in; i++) {
            cursor_in.moveToNext();
            String name = cursor_in.getString(0);
            int ea = cursor_in.getInt(1);
            int price = cursor_in.getInt(2);
            String date = cursor_in.getString(3);

            println(name +"  " + price + "��  " + ea + "��  " + date);
            money_out += price;
        }
        cursor_in.close();


        Cursor cursor_out = database.rawQuery("SELECT name, ea, price, today FROM  sell_table WHERE name = '"+selectedm+"%'", null);
        int count_out = cursor_out.getCount(); // ���� ���ڵ��� ����

        println("\n" +"����  " + count_out + " ��");

        for (int i = 0; i < count_out; i++) {
            cursor_out.moveToNext();
            String name = cursor_out.getString(0);
            int ea = cursor_out.getInt(1);
            int price = cursor_out.getInt(2);
            String date = cursor_out.getString(3);

            println(name +"  " + price + "��  " + ea + "��  " + date);
            money_in += price;
        }
        cursor_out.close();

        println("\n" +selectedm+" ���Աݾ�" +"  " + money_out+ "��  ");

        println(selectedm+" ����ݾ�" + "  " +money_in+ "��  ");
        money_result = (money_in - money_out);
        

        println(selectedm+" ����" + "  " +money_result+ "��  ");
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
