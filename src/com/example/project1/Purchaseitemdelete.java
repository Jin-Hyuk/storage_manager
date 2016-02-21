package com.example.project1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Purchaseitemdelete extends Activity {

	
		
		String databaseName_id;
	    SQLiteDatabase database_id;
	    String item_table;

	    EditText editText1;
	    EditText editText2;
	    TextView textView;
	    String selected_item;
	    public static String item_list4="";
	   ArrayAdapter<CharSequence> adapter;
	   Spinner spinner1;

	    //EditText �� �ִ� item_name �� item_price�� ���� ����
	    String item_name;
	    int item_price;


	    @Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.purchaseitemdelete);

	        editText1 = (EditText) findViewById(R.id.editText1);
	       // editText2 = (EditText) findViewById(R.id.editText2);
	       // textView = (TextView) findViewById(R.id.textView);
	        item_table = "item_table";


	        // database�� ���� �� item_table�� ����
	        databaseName_id = ((Logon)this.getApplication()).getGlobalString();

	        database_id = openOrCreateDatabase(databaseName_id, Context.MODE_PRIVATE, null);
	      //  println("�����ͺ��̽��� �������ϴ�.");


	        if (database_id != null) {
	            database_id.execSQL("CREATE TABLE if not exists " + item_table + "("
	            		    + "_id integer PRIMARY KEY autoincrement, "
		                    + "name text, "
	                    + "price integer, "
	                    + "state integer"
	                    + ")");
	        //    println("item ���̺��� ��������ϴ�.");
	        }
 Cursor ss = database_id.rawQuery("SELECT name FROM " + item_table+ " WHERE state = '1' ", null);
	        
	        

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
	          	spinner1.setPrompt("ǰ��");
	          	adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
	          	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	          spinner1.setAdapter(adapter);
	          spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
	        		@Override
	        		public void onItemSelected(AdapterView<?> parentView,
	        				View selectedView, int position, long id){
	        			item_list4 = String.valueOf(adapter.getItemId(position));
	        			}
	        		@Override
	        		public void onNothingSelected(AdapterView<?> parentView){}
	        		
	    		});
	    }
	    public void onButton3Clicked(View v) {
	        try {
	            item_name = (String) spinner1.getSelectedItem();
	          //  item_price = Integer.parseInt(editText2.getText().toString());
	        } catch(Exception e) {
	            Toast.makeText(getApplicationContext(), "�ùٸ� ������Ÿ���� �ƴմϴ�.", Toast.LENGTH_LONG).show();
	            e.printStackTrace();
	        }
	        Cursor cursor = database_id.rawQuery("SELECT name FROM " + item_table + " WHERE name='" + item_name+"'", null);
	        int count = cursor.getCount();

	        if (count == 0) {
	        	Toast.makeText(getApplicationContext(), "�������� �ʴ� item �Դϴ�.", Toast.LENGTH_LONG).show();
	          //  println("�����͸� �߰��߽��ϴ�."); // �׽�Ʈ��
	        } else {
	        	 database_id.execSQL("UPDATE item_table SET state = 0 WHERE name = '" + item_name + "';");
	            Toast.makeText(getApplicationContext(), "item�� �����߽��ϴ�.", Toast.LENGTH_LONG).show();
	        }

	       
	      //  println("item_table�� �����߽��ϴ�." + item_name + "#state=0");

	    }
	    
	
}
