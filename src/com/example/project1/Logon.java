package com.example.project1;

import android.app.Application;

public class Logon extends Application {
	private String logon;
	 
	  public String getGlobalString()
	  {
	    return logon;
	  }
	 
	  public void setGlobalString(String globalString)
	  {
	    this.logon = globalString;
	  }
	}