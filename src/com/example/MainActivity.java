package com.example;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity 
{

    private PendingIntent pendingIntent;
    
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    

    Calendar calendar = Calendar.getInstance();

//    calendar.set(Calendar.HOUR_OF_DAY, 23);
//    calendar.add(Calendar.MINUTE, 6);
    calendar.add(Calendar.SECOND, 5);
//    calendar.set(Calendar.AM_PM,Calendar.PM);
    
    Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
    
    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
   
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
	} //end onCreate
	

	
	
	
}