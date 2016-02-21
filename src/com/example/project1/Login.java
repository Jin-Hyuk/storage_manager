package com.example.project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	String databaseName;
    String databaseName_id;
    SQLiteDatabase database;
    SQLiteDatabase database_id;
	    String login_table;

	    // login에 사용할 id/pw를 받을 변수
	    String login_id;
	    int login_pw;
	    String insert_id;
	    int insert_pw;
	    EditText editText1;
	    EditText editText2;
	    EditText editText3;
	    EditText editText4;

	    String login_pwstring;
	    String insert_pwstring;


	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        startActivity(new Intent(this,Loading.class));

	        //textView = (TextView) findViewById(R.id.textViewlogin);
	        login_table = "login_table";

	        editText1 = (EditText)findViewById(R.id.editText1);
	        editText2 = (EditText)findViewById(R.id.editText2);
	        editText3 = (EditText)findViewById(R.id.editText3);
	        editText4 = (EditText)findViewById(R.id.editText4);

	        /*테이블들이 저장 될 데이터베이스 생성*/
	        databaseName = "project_1.db";
	        try {
	            database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

	         //   println("데이터베이스를 열얼습니다. : " + databaseName);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        /*create login_table*/
	        if (database != null) {
	            database.execSQL("CREATE TABLE if not exists " + login_table + "("
	                    + "_id integer PRIMARY KEY autoincrement, "
	                    + "id text, "
	                    + "pw integer"
	                    + ")");
	        //    println("테이블을 만들었습니다. : " + login_table); // 제대로 작동하나 확인용이라 지우셔도 돼요
	            database.execSQL("INSERT INTO " + login_table + "(id, pw) VALUES "
	                    + "('id', 10)");
	            }
	    }

	    // 버튼을 누르면 editText1 에 들어간 id로 pw를 검색해서 일치하는지 확인 후 로그인 성공 or 실패
	    public void onButton2Clicked(View v) {
	    	// item_ea or item_price가 비어있는 경우 메세지 출력
	        try {
	            
	        login_id = editText1.getText().toString();
	        login_pw = Integer.parseInt(editText2.getText().toString());
	        login_pwstring = String.valueOf(login_pw);
	      
	       
        }   
	        catch(Exception e) {
            Toast.makeText(getApplicationContext(), "빈칸을 입력하세요", Toast.LENGTH_LONG).show();
        }
	        if(login_id.length()==0){
	        	Toast.makeText(getApplicationContext(), "빈칸을 입력하세요", Toast.LENGTH_LONG).show();
	        }
	        

        // item_name이나 buyer_name이 비어있는 경우 메세지 출력
        if(login_id.length()!=0 && login_pwstring.length()!=0) {

	        try {
	            if(database != null) {
	                Cursor cursor = database.rawQuery("SELECT pw FROM " + login_table + " WHERE id='" + login_id +"'", null);
	                int count = cursor.getCount();

	                if (count != 0) {
	                    cursor.moveToNext();
	                    int pw = cursor.getInt(0);
	                    if (pw == login_pw) {
	                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();


	                        Logon myApp = (Logon) getApplication();
	                        myApp.setGlobalString(login_id);
	                     
	                        Log.e("Login", myApp.getGlobalString());
	                        

	                        // login 성공하면 새로운 id로 새로운 db를 열도록
	                        databaseName_id = login_id;
	                        try {
	                            database_id = openOrCreateDatabase(databaseName_id, Context.MODE_PRIVATE, null);
	                            Toast.makeText(getApplicationContext(), "데이터베이스를 열얼습니다. : " + databaseName_id, Toast.LENGTH_LONG).show();
	                            Intent intentSubActivity = 
		    							new Intent(Login.this, MainActivity.class);
		    					startActivity(intentSubActivity);
	                        } catch(Exception e) {
	                            e.printStackTrace();
	                        }
	                        // 기존에 보낸 자료는 맨 처음에 생성한 db에 테이블들을 생성했는데
	                        // 여기에서 생성한 database_id를 사용해서 테이블을 만들도록 코드 조금만 고치면 될 것 같아요.


	                    } else {
	                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();
	                        // 이 부분을 고쳐서 로그인 성공 시 다른 액티비티를 호출하도록 하면 될 것 같아요
	                    }
	                }
	                else
	                {
	                    Toast.makeText(getApplicationContext(), "존재하지 않는 id입니다.", Toast.LENGTH_LONG).show();
	                }
	                cursor.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
	    }

	    public void onButton3Clicked(View v)
	    {
	    	  try {
		            
	  	      
	        insert_id = editText3.getText().toString();
	        insert_pw = Integer.parseInt(editText4.getText().toString());
	        insert_pwstring = String.valueOf(login_pw);
	        
          } 
	    	  catch(Exception e) {
              Toast.makeText(getApplicationContext(), "빈칸을 입력하세요", Toast.LENGTH_LONG).show();
          }
	    	  if(insert_id.length()==0){
		        	Toast.makeText(getApplicationContext(), "빈칸을 입력하세요", Toast.LENGTH_LONG).show();
		        }
          // item_name이나 buyer_name이 비어있는 경우 메세지 출력
          if(insert_id.length()!=0 && insert_pwstring.length()!=0) {

	        if(database != null)
	        {
	            Cursor cursor = database.rawQuery("SELECT * FROM " + login_table + " WHERE id='"+ insert_id+"'", null);
	             int count = cursor.getCount();
	             if(count != 0) {
	                 Toast.makeText(getApplicationContext(), "이미 가입된 id입니다.", Toast.LENGTH_LONG).show();
	             } else {
	                 //database.execSQL("INSERT INTO " + login_table + "(id, pw) VALUES " + "('" + insert_id + "', " + insert_pw + ")");
	                 database.execSQL("INSERT INTO " + login_table + "(id, pw) VALUES " + "('" + insert_id + "', " + insert_pw + ")");
	                 Toast.makeText(getApplicationContext(), "회원가입이 완료됐습니다.", Toast.LENGTH_LONG).show();
	             }
	            cursor.close();
	        }
          }
	    }
	    
	    public void onButton1Clicked (View v) {

	        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-8595-0436"));
	        startActivity(intent);
	        }
	    
	}
	   
