package edu.wschain.china_d_notifition;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import io.flutter.app.FlutterActivity;


public class NotifitionActivity extends Activity {
    private static final String TAG = "NotifitionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate: 跳转了");

    }
}
