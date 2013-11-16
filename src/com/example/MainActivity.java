package com.example;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity 
{
	private Button create;
	private Button cancel;
	private int Alarm_ID = 1;
    private PendingIntent DispIntent;
    
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
	    calendar.add(Calendar.SECOND, 10);
		
	    Intent AlarmIntent = new Intent(this, MyReceiver.class);
	    DispIntent = PendingIntent.getBroadcast(this, 0, AlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	    AlarmIntent.setData(Uri.parse("custom://" + Alarm_ID));
	    AlarmIntent.setAction(String.valueOf(Alarm_ID));
	    
	    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), DispIntent);
	    
	    Toast.makeText(this,"Created Alarm...wait 5 seconds" ,Toast.LENGTH_SHORT).show();
	}
	
	private void cancel_Alarm()
	{
		Intent AlarmIntent = new Intent(this, MyReceiver.class);
		AlarmIntent.setData(Uri.parse("custom://" + Alarm_ID));
		AlarmIntent.setAction(String.valueOf(Alarm_ID));
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		PendingIntent displayIntent = PendingIntent.getBroadcast(this, 0, AlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager.cancel(displayIntent);
		
		Toast.makeText(this,"Alarm Cancelled...supposedly" ,Toast.LENGTH_SHORT).show();
	}
	
	
}