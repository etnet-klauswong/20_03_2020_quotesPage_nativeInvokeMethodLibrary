package com.etnet.nativeinvokeflutter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import io.flutter.plugin.common.EventChannel.StreamHandler;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class ETnet_FlutterActivity extends FlutterActivity {

    private static final String quotesPageEventChannel = "samples.flutter.io/quotesPageEvent";


    private static final String FID_CURRENCY = "HK";
    private static final int SendRequest = 1;
    private static final String CHANNEL = "samples.flutter.io/platform_view";
    private static final String METHOD_SWITCH_VIEW = "switchView";
    private static final int COUNT_REQUEST = 1;
    public static  MethodChannel.Result result;
    public static EventChannel.EventSink eventSink;
    public static MethodChannel methodChannel;
    public static Map quotesPageMap;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                        ETnet_FlutterActivity.this.result = result;
                        HashMap count = methodCall.arguments();
                        System.out.println("MethodChannel Receive Data: " + String.valueOf(count));
                        if (methodCall.method.equals(METHOD_SWITCH_VIEW)) {
                            System.out.println("fUCK yIU");
                            onLaunchFullScreen(count);
                        } else {
                            result.notImplemented();
                        }
                    }
                }
        );

        new EventChannel(flutterEngine.getDartExecutor(), quotesPageEventChannel).setStreamHandler(
                new StreamHandler() {
                    private BroadcastReceiver quotesPageReceiver;
                    @Override
                    public void onListen(Object arguments, EventChannel.EventSink events) {
                        quotesPageReceiver = createQuotesPageChangeReceiver(events);
                        registerReceiver(
                                quotesPageReceiver, new IntentFilter(Intent.ACTION_SEND));
                    }

                    @Override
                    public void onCancel(Object arguments) {
                        unregisterReceiver(quotesPageReceiver);
                        quotesPageReceiver = null;
                    }
                }
        );
    }

    private void onLaunchFullScreen(Map count) {
        Intent fullScreenIntent = new Intent(this, MainActivity.class);
//        fullScreenIntent.putExtra(MainActivity.EXTRA_Map, count);
        startActivityForResult(fullScreenIntent, COUNT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COUNT_REQUEST) {
            if (resultCode == RESULT_OK) {
                System.out.println("fuuck");
//                result.success(data.getIntExtra(MainActivity.EXTRA_COUNTER, 5));
            } else {
                result.error("ACTIVITY_FAILURE", "Failed while launching activity", null);
            }
        }
    }

    public BroadcastReceiver createQuotesPageChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(Intent.AC)
                }
            }
        };
    }

    public static void transmitData(@NonNull Map transmitData){
        result.success(transmitData);
    }


}
