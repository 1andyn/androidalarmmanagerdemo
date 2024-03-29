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
	
	/* Bundle & Extra Keys */
	private final static String EV_NAME = "event_name";
	private final static String EV_DESC = "event_desc";
    private String name = "Change Event Name to This!";
    private String desc = "Change Description to This!";
    
	private int Alarm_ID = 1337; // USED THIS ID TO KEEP TRACK OF YOUR ALARMS!
    
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
		
	    Intent AlarmIntent = new Intent().setClass(this, MyReceiver.class);
	    AlarmIntent.setData(Uri.parse("custom://" + Alarm_ID));
	    AlarmIntent.setAction(String.valueOf(Alarm_ID));

	    AlarmIntent.putExtra(EV_NAME, name);
	    AlarmIntent.putExtra(EV_DESC, desc);
	    
	    PendingIntent DispIntent = PendingIntent.getBroadcast(this.getApplicationContext(), Alarm_ID, 
	    		AlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

	    /* Scheduling the Alarm to be triggered*/
	    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), DispIntent);
	    
	    Toast.makeText(this,"Created Alarm...wait 5 seconds" ,Toast.LENGTH_SHORT).show();
	}
	
	private void cancel_Alarm()
	{
		/* Recreate the alarm creation data */
		Intent AlarmIntent = new Intent(this, MyReceiver.class);    
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		AlarmIntent.setData(Uri.parse("custom://" + Alarm_ID));
		AlarmIntent.setAction(String.valueOf(Alarm_ID));
		PendingIntent DispIntent = PendingIntent.getBroadcast(this.getApplicationContext(), Alarm_ID, 
				AlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		/* Instead of setting an alarm, use cancel on the pending Intent*/
		alarmManager.cancel(DispIntent);
		
		Toast.makeText(this,"Alarm Cancelled." ,Toast.LENGTH_SHORT).show();
	}
	
	
}