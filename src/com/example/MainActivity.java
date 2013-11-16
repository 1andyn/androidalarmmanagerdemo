package com.example;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity 
{
	private Button create;
	private Button cancel;
	
    private PendingIntent pendingIntent;
    
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    initialize_Layout();
	}
	
	private void initialize_Layout()
	{
		create = (Button)findViewById(R.id.Create_Alarm);
		cancel = (Button)findViewById(R.id.Cancel_Alarm);
		set_Listeners();
	}
	
	private void set_Listeners()
	{
		create.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        create_Alarm();
		    }
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        cancel_Alarm();
		    }
		});
	
	}
	
	private void create_Alarm()
	{
		/* Instantiate a Calendar */ 
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.SECOND, 5);
		
	    Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
	    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
	    
	    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
	}
	
	private void cancel_Alarm()
	{
		
	}
	
	
}