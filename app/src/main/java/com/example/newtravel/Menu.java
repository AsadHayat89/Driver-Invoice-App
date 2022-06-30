package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newtravel.RecycleSearchPrinter.PrinterResult;
import com.example.newtravel.Settings.SecurityPin;
import com.example.newtravel.Static.StaticHolder;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Method;

public class Menu extends AppCompatActivity {
    private Button Inv,Sale,SaF,Setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initwidget();
        callListner();

        String getLogin=getIntent().getExtras().getString("LoggedIn");
        if(getLogin.equals("True")){

            SharedPreferences prefs = getSharedPreferences("Project", MODE_PRIVATE);
            String name = prefs.getString("Password", null);//"No name defined" is the default value.

            if(!TextUtils.isEmpty(name)){
                Intent i=new Intent(Menu.this, SecurityPin.class);
                i.putExtra("Type","Login");
                startActivity(i);
                finish();
            }
            else {
//            SharedPreferences mPrefs = getSharedPreferences("Project", Context.MODE_PRIVATE);
//            Gson gson = new Gson();
//            String json = mPrefs.getString("Device", "");
//            BluetoothDevice obj = gson.fromJson(json, BluetoothDevice.class);
//
//            // Toast.makeText(Menu.this, String.valueOf(StaticHolder.mmDevice.getName()), Toast.LENGTH_SHORT).show();
//            if (obj != null) {
////            Toast.makeText(Menu.this, String.valueOf(StaticHolder.mmDevice.getName()), Toast.LENGTH_SHORT).show();
//                IntentFilter filter = new IntentFilter();
//                filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
//                filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
//                filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
//                this.registerReceiver(broadcastReceiver, filter);
//            } else if (obj == null) {
//                Toast.makeText(Menu.this, "Please Connect you Printer", Toast.LENGTH_SHORT).show();
//            }
            }
        }


    }
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        BluetoothDevice device;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Log.d("GetConnection","Gotit");
                Toast.makeText(Menu.this, "Device is now Connected",    Toast.LENGTH_SHORT).show();
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Log.d("GetConnection","Gotop");
                Toast.makeText(Menu.this, "Device is disconnected",       Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void initwidget(){
        Inv=findViewById(R.id.btnSMLI);
        Sale=findViewById(R.id.btnSMLA);
        SaF=findViewById(R.id.btnSMBN);
        Setting=findViewById(R.id.btnSMD);
    }
    public static boolean isConnected(BluetoothDevice device) {
        try {
            Method m = device.getClass().getMethod("isConnected", (Class[]) null);
            boolean connected = (boolean) m.invoke(device, (Object[]) null);
            return connected;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    private void callListner() {
        Inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,InvoiceMenu.class);
                startActivity(i);
            }
        });
        Sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,SalesMenu.class);
                startActivity(i);
            }
        });
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,SettingMenu.class);
                startActivity(i);
            }
        });
        SaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,SafMenu.class);
                startActivity(i);
            }
        });
    }
}