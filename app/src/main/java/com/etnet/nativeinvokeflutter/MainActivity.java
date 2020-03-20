package com.etnet.nativeinvokeflutter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.HashMap;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

// TODO: It is a default Activity
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_COUNTER = "counter";
    public static final HashMap EXTRA_Map = new HashMap<>();

    private static final String METHOD_SWITCH_VIEW = "switchView";
    private static final int COUNT_REQUEST = 1;

    private int counter;
    private Button transmitDataButton;
    private EditText hashMapKeyName;
    private EditText hashMapKeyValue;
    private FlutterEngine flutterEngine;
    private MethodChannel.Result resuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hashMapInit();


        counter = getIntent().getIntExtra(EXTRA_COUNTER, 0);
        handleTransmissionUI();
    }
    public void hashMapInit(){
        EXTRA_Map.put("FID_CURRENCY", "HK");
        EXTRA_Map.put("FID_NOMINAL",1.20);
        EXTRA_Map.put("FID_CHANGE", 1.700);
        EXTRA_Map.put("FID_HIGH", 71.150);
        EXTRA_Map.put("FID_OPEN", 70.600);
        EXTRA_Map.put("FID_LOW", 69.150);
        EXTRA_Map.put("FID_PER_CHANGE", 1.700);
        EXTRA_Map.put("FID_CLOSE", 70.600);
        EXTRA_Map.put("FID_TURNOVER", 334442283.918);
        EXTRA_Map.put("FID_VOLUME", 4803689.0);
        EXTRA_Map.put("FID_NO_OF_TRANS", 1560);
        EXTRA_Map.put("FID_BOARDLOT", 500);
        EXTRA_Map.put("FID_VALUE", 3.17);
        EXTRA_Map.put("FID_DPS", 3.17);
        EXTRA_Map.put("FID_PE_RATIO", 6.869);
        EXTRA_Map.put("FID_PERCENT_YIELD", 4.564);

        EXTRA_Map.put("FID_EXPECT_PE_RATIO", 9999);
        EXTRA_Map.put("FID_EXPECT_PERCENT_YIELD", 99999);
        EXTRA_Map.put("FID_1M_HIGH", 73.3);
        EXTRA_Map.put("FID_52W_HIGH", 88.28);
        EXTRA_Map.put("FID_1M_LOW", 65.8);
        EXTRA_Map.put("FID_52W_LOW", 63.43);
        EXTRA_Map.put("FID_RSI14", 51.404);
        EXTRA_Map.put("FID_SMA_10", 71.39);
        EXTRA_Map.put("FID_MARKET_CAP", 267815902);
        EXTRA_Map.put("FID_SMA_20", 69.806);
        EXTRA_Map.put("FID_SHORTSELL", 22439375.0);
        EXTRA_Map.put("FID_SMA_50", 70.821);
    }

    public void handleTransmissionUI() {
        transmitDataButton = (Button) findViewById(R.id.transmitToFlutterB1);
        hashMapKeyName = (EditText) findViewById(R.id.QuotesPageKey);
        hashMapKeyValue = (EditText) findViewById(R.id.QuotesPageValue);
        transmitDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyName = hashMapKeyName.getText().toString();
                String keyValue = hashMapKeyValue.getText().toString();
                ETnet_FlutterActivity.result.success(EXTRA_Map);
                returnToFlutterView();
            }
        });
    }

    private void updateFID() {
        String FID_CURRENCY = hashMapKeyValue.getText().toString();
    }

    private void returnToFlutterView() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_COUNTER, counter);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void onBackPressed() {
        returnToFlutterView();
    }
}
