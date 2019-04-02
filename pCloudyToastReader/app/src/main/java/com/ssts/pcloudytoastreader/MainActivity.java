package com.ssts.pcloudytoastreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KULDEEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final IntentFilter mIntentFilter = new IntentFilter(MyAccessibilityService.Constants.ACTION_CATCH_NOTIFICATION);
        mIntentFilter.addAction(MyAccessibilityService.Constants.ACTION_CATCH_TOAST);
        registerReceiver(toastOrNotificationCatcherReceiver, mIntentFilter);
        Log.v(TAG, "Receiver registered.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(toastOrNotificationCatcherReceiver);
    }

    private final BroadcastReceiver toastOrNotificationCatcherReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "Received message");
            Log.v(TAG, "intent.getAction() :: " + intent.getAction());
            Log.v(TAG, "intent.getStringExtra(Constants.EXTRA_PACKAGE) :: " + intent.getStringExtra(MyAccessibilityService.Constants.EXTRA_PACKAGE));
            Log.v(TAG, "intent.getStringExtra(Constants.EXTRA_MESSAGE) :: " + intent.getStringExtra(MyAccessibilityService.Constants.EXTRA_MESSAGE));
        }
    };
}
