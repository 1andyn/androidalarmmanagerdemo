package com.example;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
                           
public class MyAlarmService extends Service{
	
	private Bitmap icon;
	private Uri sound;
	private int ledinterval = 1000; // Milliseconds
	private String ev_name; // Event name
	private String desc_name; // Desc name
	private final static String note_tick = "Planner Plus Notification!";
	private int ledcolor = 0xff0000ff; // LED Color for Notification (will become Dynamic
	private final long[] pattern = {100, 100, 100, 100, 100, 100}; // Vibrate Pattern
	
     private NotificationManager mManager;

     @Override
     public IBinder onBind(Intent arg0)
     {
        return null;
     }

    @Override
    public void onCreate() 
    {
    	initialization();
    }
   
    /* Function for initializing Variables */
   private void initialization()
   {
	   icon = BitmapFactory.decodeResource(this.getApplicationContext().getResources(),
               R.drawable.patrick);
	   sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
   }
    
   @SuppressWarnings("static-access")
   @Override
   public int onStartCommand(Intent intent, int flags, int startId)
   {
       super.onStartCommand(intent, flags, startId);
     
       mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
	   Intent OpenIntent = new Intent(this.getApplicationContext(),MainActivity.class);
	   /* Pending Intent is for triggering Alarm */
	   PendingIntent pendingNoteIntent = PendingIntent.getActivity(this.getApplicationContext(),0, OpenIntent,
			   PendingIntent.FLAG_UPDATE_CURRENT);

	   /* Notification Creation Code*/
	   Notification notification = new Notification.Builder(getApplicationContext())
       .setContentTitle("Event Title")
       .setContentText("Got to fill up gas tank for car")
       .setTicker("Planner Plus Notification!")
       .setSmallIcon(R.drawable.ic_action_event)
       .setLargeIcon(icon)
       .setSound(sound)
       .setVibrate(pattern)
       .setLights(ledcolor, ledinterval, ledinterval)
       .build();

	   // Hide Notification after it is selected
	   OpenIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
       notification.flags |= Notification.FLAG_AUTO_CANCEL;
	   
       mManager.notify(0, notification);
       return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() 
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void setNameDesc(String name, String desc)
    {
    	ev_name = name; // Event name
    	desc_name = desc; // Desc name
    }
    
}