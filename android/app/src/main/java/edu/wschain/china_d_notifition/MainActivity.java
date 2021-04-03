package edu.wschain.china_d_notifition;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                getNotifition();
//            }
//        };
//
//        timer.schedule(timerTask,3000);
//        timer.cancel();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    getNotifition();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getNotifition() {
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        String id = "01";
        String name = "name";
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("时间到")
                    .setContentText("时间到啦！！！")
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setFullScreenIntent(PendingIntent.getActivity(this,0,new Intent(),0),true)
                    .build();
        }else{
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setAutoCancel(true).setContentTitle("时间到").setContentText("时间到了！！").setSmallIcon(R.mipmap.ic_launcher);
            builder.setFullScreenIntent(PendingIntent.getActivity(this,0,new Intent(),0),true);
            notification = builder.build();
        }
        notificationManager.notify(0,notification);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    notificationManager.cancel(0);  //停止消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
