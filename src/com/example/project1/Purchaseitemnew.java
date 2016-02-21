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

	    //EditText 에 있는 item_name 과 item_price를 받을 변수
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


	        // database를 생성 및 item_table을 생성
	        databasename_id = ((Logon)this.getApplication()).getGlobalString();


	        database_id = openOrCreateDatabase(databasename_id, Context.MODE_PRIVATE, null);
	       // println("데이터베이스를 열었습니다.");


	        if (database_id != null) {
	            database_id.execSQL("CREATE TABLE if not exists " + item_table + "("
	                    + "_id integer PRIMARY KEY autoincrement, "
	                    + "name text, "
	                    + "price integer, "
	                    + "state integer"
	                    + ")");
	          //  println("item 테이블을 만들었습니다.");
	        }
	        
	    }


	    // 버튼을 누르면 item_name과 item_price를 받아서 item_table에 등록
	    public void onButton1Clicked(View v) {
	    	if(editText1.getText().toString().length() == 0){
	    		Toast.makeText(getApplicationContext(), "물품명을 입력 하세요", Toast.LENGTH_LONG).show();
	    		
	    	}
	    	else{

	        // item_price 에 int 타입이 아니었을 때 예외 처리
	        try {
	            item_name = editText1.getText().toString();
	            item_price = Integer.parseInt(editText2.getText().toString());
	        } catch(Exception e) {
	            Toast.makeText(getApplicationContext(), "올바른 데이터타입이 아닙니다.", Toast.LENGTH_LONG).show();
	            e.printStackTrace();
	        }

	        //제대로 값을 받았는지 테스트용
	    //    println("("+item_name+", "+item_price+")");

	        // item_table에 없는 경우만 넣음
	        
	        
	        Cursor cursor = database_id.rawQuery("SELECT name, state FROM " + item_table + " WHERE name='" + item_name+"'", null);
	        int count = cursor.getCount();
	        
	       
	        int state1 = 1;
	        
	        if (count == 0) {
	            database_id.execSQL("INSERT INTO " + item_table + "(name, price, state) VALUES "
	                    + "('" + item_name + "', " + item_price + ", 1)");
	            Toast.makeText(getApplicationContext(), "품목 추가 되었습니다.", Toast.LENGTH_LONG).show();
	         //   println("데이터를 추가했습니다."); // 테스트용
	        } else {
	        	cursor.moveToFirst();
	        	 int state0 = cursor.getInt(1);
	        	if (state0 == 0){
	        		
	        		database_id.execSQL("UPDATE item_table SET state = " + state1 + " WHERE name = '"+ item_name +"';");
	        		Toast.makeText(getApplicationContext(), "삭제된 품목을 되돌렸습니다.", Toast.LENGTH_LONG).show();
	        	}
	        	else
	        	{
	        		
	            Toast.makeText(getApplicationContext(), "이미 존재하는 item 입니다.", Toast.LENGTH_LONG).show();
	        	}
	        }
	    }}
}
	        



