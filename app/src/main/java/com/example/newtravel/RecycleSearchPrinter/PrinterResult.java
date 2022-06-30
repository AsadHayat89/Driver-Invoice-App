package com.example.newtravel.RecycleSearchPrinter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.Menu;
import com.example.newtravel.R;
import com.example.newtravel.Recyle.Items;
import com.example.newtravel.Static.StaticHolder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.UUID;

public class PrinterResult extends AppCompatActivity {
    private TextView head;
    private ProgressBar proG;
    private ImageView img;
    private Button click;

    BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();;
    BluetoothSocket mmSocket;

    // needed for communication to bluetooth device / network
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    private PrinterItems items;
    private BluetoothDevice newDevice;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_result);
        intwidget();
        clickListner();

        Log.d("DeviceName","items.getDevice().getName()");
        newDevice =  getIntent().getExtras().getParcelable("Print");
       // Log.d("DeviceName",newDevice.getName());
        try{

            openBT();

            IntentFilter filter = new IntentFilter();
            filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
            filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
            filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
            this.registerReceiver(broadcastReceiver, filter);

            if(isConnected(newDevice)){
                //Toast.makeText(PrinterResult.this, "Connected", Toast.LENGTH_SHORT).show();
                head.setText(newDevice.getName()+" Connected");
                head.setTextColor(R.color.green);
                proG.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.rt);
                StaticHolder.checkConnection=true;
                StaticHolder.mmDevice=newDevice;
                SharedPreferences mPrefs = getSharedPreferences("Project",Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(newDevice);
                prefsEditor.putString("Device", json);
                prefsEditor.commit();
            }
            else{
                //Toast.makeText(PrinterResult.this, "Not Connected", Toast.LENGTH_SHORT).show();
                head.setText(newDevice.getName()+" Not Connected");
                head.setTextColor(R.color.design_default_color_error);
                proG.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.afterfalse);
                StaticHolder.checkConnection=false;
            }
        }catch (Exception e){

        };
    }

    private void clickListner() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PrinterResult.this, Menu.class);
                startActivity(i);
                finish();
            }
        });
    }

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        BluetoothDevice device;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Log.d("GetConnection","Gotit");
                Toast.makeText(PrinterResult.this, "Device is now Connected",    Toast.LENGTH_SHORT).show();
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Log.d("GetConnection","Gotop");
                Toast.makeText(PrinterResult.this, "Device is disconnected",       Toast.LENGTH_SHORT).show();
            }
        }
    };
    public static boolean isConnected(BluetoothDevice device) {
        try {
            Method m = device.getClass().getMethod("isConnected", (Class[]) null);
            boolean connected = (boolean) m.invoke(device, (Object[]) null);
            return connected;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    void openBT() throws IOException {
        try {

            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = StaticHolder.mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();

            //myLabel.setText("Bluetooth Opened");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();

                            if (bytesAvailable > 0) {

                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
                                                //myLabel.setText(data);
                                              //  Toast.makeText(mContext.get(), String.valueOf(data), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void intwidget() {
        head=findViewById(R.id.TxtResultView);
        proG=findViewById(R.id.progressConnect);
        img=findViewById(R.id.ResultImgV);
        click=findViewById(R.id.btnokclick);
    }
}