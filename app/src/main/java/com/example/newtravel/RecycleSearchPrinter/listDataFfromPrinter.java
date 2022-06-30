package com.example.newtravel.RecycleSearchPrinter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.R;
import com.example.newtravel.Recyle.RecyclerviewItemAdapter;
import com.example.newtravel.Static.StaticHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class listDataFfromPrinter extends AppCompatActivity {
    BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();;
    BluetoothSocket mmSocket;

    private ArrayList<PrinterItems> itemsList=new ArrayList<>();
    int io;
    private ProgressBar press;
    private TextView Cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdataforprinter);

        press=findViewById(R.id.listProgress);
        Cont=findViewById(R.id.txtlistConnecting);

        intwidget();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // displayData();
                press.setVisibility(View.INVISIBLE);
                Cont.setVisibility(View.INVISIBLE);
            }
        }, 120000);



        findBT();
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
        mBluetoothAdapter.startDiscovery();
        paireDevice();
    }
    private void paireDevice(){
        RecyclerviewPrinterAdapter recyclerviewItemAdapter = new RecyclerviewPrinterAdapter(itemsList);

        RecyclerView recyclerView = findViewById(R.id.recycleViewPrinter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewItemAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                //showToast("Found device " + device.getName());
                //Log.d("FoundDevice",device.getName());

                    itemsList.add(new PrinterItems(device.getName(), device));
                    if (itemsList == null) {
                        //Toast.makeText(listDataFfromPrinter.this, "No device found", Toast.LENGTH_LONG);
                    } else {

                        paireDevice();
                    }
                    if(itemsList.contains(new PrinterItems(device.getName(), device))){
                        Toast.makeText(listDataFfromPrinter.this, "Aginr", Toast.LENGTH_LONG);
                }

            }
        }
    };    // this will find a bluetooth printer device
    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                Toast.makeText(listDataFfromPrinter.this,"Bluetooth adapater is not avaiable",Toast.LENGTH_LONG).show();
            }

            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {

                    itemsList.add(new PrinterItems(device.getName().toString(),device));
                }

            }



            //Toast.makeText(listDataFfromPrinter.this, "Bluetooth device found", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void intwidget() {

    }
}