package com.example;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.widget.Toast;
                           

public class MyAlarmService extends Service 

{
     private NotificationManager mManager;

     @Override
     public IBinder onBind(Intent arg0)
     {
       // TODO Auto-generated method stub
        return null;
     }

    @Override
    public void onCreate() 
    {
    	Toast.makeText(getApplicationContext(), "Service Created", 1).show();
       // TODO Auto-generated method stub  
       super.onCreate();
    }

   @SuppressWarnings("static-access")
   @Override
   public int onStartCommand(Intent intent, int flags, int startId)
   {
       super.onStartCommand(intent, flags, startId);
     
       mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
	   Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
	   
	   Bitmap icon = BitmapFactory.decodeResource(this.getApplicationContext().getResources(),
               R.drawable.patrick);
	   
	   Notification notification = new Notification.Builder(getApplicationContext())
       .setContentTitle("This is an Event Title")
       .setContentText("This is the event Subject")
       .setSmallIcon(R.drawable.ic_launcher)
       .setLargeIcon(icon)
       .build();
	   
	   //Notification notification = new Notification(R.drawable.ic_launcher,"This is a test message!", System.currentTimeMillis());
	
	   intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

	   PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
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

}