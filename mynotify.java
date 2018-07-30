package com.example.sankalp.safecity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.util.Calendar;
import java.util.Date;

public class mynotify extends Service {
    NotificationManager nm ;
    Notification.Builder nb;
    String n;

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {


        n=intent.getStringExtra("id");
        getnotify();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }
    public void getnotify()
    {
        nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nb=new Notification.Builder(this);
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setTicker("notification recieved");
        nb.setContentTitle("New Complaint");
        nb.setContentText("complaint id:"+n);
        Date dt= Calendar.getInstance().getTime();
        nb.setSubText(dt.toString());

        nb.setAutoCancel(true);
        Notification n=nb.build();
        nm.notify(1,n);
    }
}