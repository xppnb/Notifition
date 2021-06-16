package edu.wschain.china_d_notifition;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {

    private NotificationCompat.Builder builder;
    private Notification notification;

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

        //获取Notifition对象
        NotificationManager notificationManager = (NotificationManager)
                getContext().getSystemService(NOTIFICATION_SERVICE);
        String id = "0";
        String name = "name";
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) { //当SDk > 8.0 26 以后需要设置Channel
            NotificationChannel notificationChannel =
                    new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //设置跳转
        Intent intent = new Intent(this, NotifitionActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, intent, 0);

        builder = new NotificationCompat.Builder(this, id)
                .setContentTitle("时间到")
                .setContentText("时间到啦！！！")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.view))
                .setContentIntent(pendingIntent)
                .setColor(Color.parseColor("#FF0000"))
                .setAutoCancel(true)
        ;
        notification = builder.build();
        notificationManager.notify(0, notification);

    }
}
