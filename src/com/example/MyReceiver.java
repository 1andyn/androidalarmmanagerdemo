package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver
{
	private final static String EV_NAME = "event_name";
	private final static String EV_DESC = "event_desc"; 
	
	@Override
	 public void onReceive(Context context, Intent intent)
	{
	   Intent service1 = new Intent(context, MyAlarmService.class);
	   
       Bundle extras = intent.getExtras();
	   if(extras != null){
		    service1.putExtra(EV_NAME, extras.getString(EV_NAME));
		    service1.putExtra(EV_DESC, extras.getString(EV_DESC));
	   } else {
		   System.out.println("Bundle has been detected as empty");
	   }
	
	   context.startService(service1);
	   
	 }
	
}
