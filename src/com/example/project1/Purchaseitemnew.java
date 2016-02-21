package com.example.project1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Purchaseitemnew extends Activity { 

	
		String databasename_id;
	    SQLiteDatabase database_id;
	    String item_table;

	    EditText editText1;
	    EditText editText2;
	  //  TextView textView;

	    //EditText �� �ִ� item_name �� item_price�� ���� ����
	    String item_name;
	    int item_price;


	    @Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.purchaseitemnew);

	        editText1 = (EditText) findViewById(R.id.editText1);
	        editText2 = (EditText) findViewById(R.id.editText2);
	      //  textView = (TextView) findViewById(R.id.textView);
	        item_table = "item_table";


	        // database�� ���� �� item_table�� ����
	        databasename_id = ((Logon)this.getApplication()).getGlobalString();


	        database_id = openOrCreateDatabase(databasename_id, Context.MODE_PRIVATE, null);
	       // println("�����ͺ��̽��� �������ϴ�.");


	        if (database_id != null) {
	            database_id.execSQL("CREATE TABLE if not exists " + item_table + "("
	                    + "_id integer PRIMARY KEY autoincrement, "
	                    + "name text, "
	                    + "price integer, "
	                    + "state integer"
	                    + ")");
	          //  println("item ���̺��� ��������ϴ�.");
	        }
	        
	    }


	    // ��ư�� ������ item_name�� item_price�� �޾Ƽ� item_table�� ���
	    public void onButton1Clicked(View v) {
	    	if(editText1.getText().toString().length() == 0){
	    		Toast.makeText(getApplicationContext(), "��ǰ���� �Է� �ϼ���", Toast.LENGTH_LONG).show();
	    		
	    	}
	    	else{

	        // item_price �� int Ÿ���� �ƴϾ��� �� ���� ó��
	        try {
	            item_name = editText1.getText().toString();
	            item_price = Integer.parseInt(editText2.getText().toString());
	        } catch(Exception e) {
	            Toast.makeText(getApplicationContext(), "�ùٸ� ������Ÿ���� �ƴմϴ�.", Toast.LENGTH_LONG).show();
	            e.printStackTrace();
	        }

	        //����� ���� �޾Ҵ��� �׽�Ʈ��
	    //    println("("+item_name+", "+item_price+")");

	        // item_table�� ���� ��츸 ����
	        
	        
	        Cursor cursor = database_id.rawQuery("SELECT name, state FROM " + item_table + " WHERE name='" + item_name+"'", null);
	        int count = cursor.getCount();
	        
	       
	        int state1 = 1;
	        
	        if (count == 0) {
	            database_id.execSQL("INSERT INTO " + item_table + "(name, price, state) VALUES "
	                    + "('" + item_name + "', " + item_price + ", 1)");
	            Toast.makeText(getApplicationContext(), "ǰ�� �߰� �Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
	         //   println("�����͸� �߰��߽��ϴ�."); // �׽�Ʈ��
	        } else {
	        	cursor.moveToFirst();
	        	 int state0 = cursor.getInt(1);
	        	if (state0 == 0){
	        		
	        		database_id.execSQL("UPDATE item_table SET state = " + state1 + " WHERE name = '"+ item_name +"';");
	        		Toast.makeText(getApplicationContext(), "������ ǰ���� �ǵ��Ƚ��ϴ�.", Toast.LENGTH_LONG).show();
	        	}
	        	else
	        	{
	        		
	            Toast.makeText(getApplicationContext(), "�̹� �����ϴ� item �Դϴ�.", Toast.LENGTH_LONG).show();
	        	}
	        }
	    }}
}
	        



