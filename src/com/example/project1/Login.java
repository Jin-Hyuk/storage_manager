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

	    // login�� ����� id/pw�� ���� ����
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

	        /*���̺���� ���� �� �����ͺ��̽� ����*/
	        databaseName = "project_1.db";
	        try {
	            database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

	         //   println("�����ͺ��̽��� ������ϴ�. : " + databaseName);
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
	        //    println("���̺��� ��������ϴ�. : " + login_table); // ����� �۵��ϳ� Ȯ�ο��̶� ����ŵ� �ſ�
	            database.execSQL("INSERT INTO " + login_table + "(id, pw) VALUES "
	                    + "('id', 10)");
	            }
	    }

	    // ��ư�� ������ editText1 �� �� id�� pw�� �˻��ؼ� ��ġ�ϴ��� Ȯ�� �� �α��� ���� or ����
	    public void onButton2Clicked(View v) {
	    	// item_ea or item_price�� ����ִ� ��� �޼��� ���
	        try {
	            
	        login_id = editText1.getText().toString();
	        login_pw = Integer.parseInt(editText2.getText().toString());
	        login_pwstring = String.valueOf(login_pw);
	      
	       
        }   
	        catch(Exception e) {
            Toast.makeText(getApplicationContext(), "��ĭ�� �Է��ϼ���", Toast.LENGTH_LONG).show();
        }
	        if(login_id.length()==0){
	        	Toast.makeText(getApplicationContext(), "��ĭ�� �Է��ϼ���", Toast.LENGTH_LONG).show();
	        }
	        

        // item_name�̳� buyer_name�� ����ִ� ��� �޼��� ���
        if(login_id.length()!=0 && login_pwstring.length()!=0) {

	        try {
	            if(database != null) {
	                Cursor cursor = database.rawQuery("SELECT pw FROM " + login_table + " WHERE id='" + login_id +"'", null);
	                int count = cursor.getCount();

	                if (count != 0) {
	                    cursor.moveToNext();
	                    int pw = cursor.getInt(0);
	                    if (pw == login_pw) {
	                        Toast.makeText(getApplicationContext(), "�α��� ����", Toast.LENGTH_LONG).show();


	                        Logon myApp = (Logon) getApplication();
	                        myApp.setGlobalString(login_id);
	                     
	                        Log.e("Login", myApp.getGlobalString());
	                        

	                        // login �����ϸ� ���ο� id�� ���ο� db�� ������
	                        databaseName_id = login_id;
	                        try {
	                            database_id = openOrCreateDatabase(databaseName_id, Context.MODE_PRIVATE, null);
	                            Toast.makeText(getApplicationContext(), "�����ͺ��̽��� ������ϴ�. : " + databaseName_id, Toast.LENGTH_LONG).show();
	                            Intent intentSubActivity = 
		    							new Intent(Login.this, MainActivity.class);
		    					startActivity(intentSubActivity);
	                        } catch(Exception e) {
	                            e.printStackTrace();
	                        }
	                        // ������ ���� �ڷ�� �� ó���� ������ db�� ���̺���� �����ߴµ�
	                        // ���⿡�� ������ database_id�� ����ؼ� ���̺��� ���鵵�� �ڵ� ���ݸ� ��ġ�� �� �� ���ƿ�.


	                    } else {
	                        Toast.makeText(getApplicationContext(), "��й�ȣ�� Ʋ���ϴ�.", Toast.LENGTH_LONG).show();
	                        // �� �κ��� ���ļ� �α��� ���� �� �ٸ� ��Ƽ��Ƽ�� ȣ���ϵ��� �ϸ� �� �� ���ƿ�
	                    }
	                }
	                else
	                {
	                    Toast.makeText(getApplicationContext(), "�������� �ʴ� id�Դϴ�.", Toast.LENGTH_LONG).show();
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
              Toast.makeText(getApplicationContext(), "��ĭ�� �Է��ϼ���", Toast.LENGTH_LONG).show();
          }
	    	  if(insert_id.length()==0){
		        	Toast.makeText(getApplicationContext(), "��ĭ�� �Է��ϼ���", Toast.LENGTH_LONG).show();
		        }
          // item_name�̳� buyer_name�� ����ִ� ��� �޼��� ���
          if(insert_id.length()!=0 && insert_pwstring.length()!=0) {

	        if(database != null)
	        {
	            Cursor cursor = database.rawQuery("SELECT * FROM " + login_table + " WHERE id='"+ insert_id+"'", null);
	             int count = cursor.getCount();
	             if(count != 0) {
	                 Toast.makeText(getApplicationContext(), "�̹� ���Ե� id�Դϴ�.", Toast.LENGTH_LONG).show();
	             } else {
	                 //database.execSQL("INSERT INTO " + login_table + "(id, pw) VALUES " + "('" + insert_id + "', " + insert_pw + ")");
	                 database.execSQL("INSERT INTO " + login_table + "(id, pw) VALUES " + "('" + insert_id + "', " + insert_pw + ")");
	                 Toast.makeText(getApplicationContext(), "ȸ�������� �Ϸ�ƽ��ϴ�.", Toast.LENGTH_LONG).show();
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
	   
