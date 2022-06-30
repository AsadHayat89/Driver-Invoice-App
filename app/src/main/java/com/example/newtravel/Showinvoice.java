package com.example.newtravel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.Static.StaticHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.security.Key;
import java.util.ArrayList;
import java.util.UUID;

public class Showinvoice extends AppCompatActivity {
    private String key1,Store;
    ArrayList<String> data=new ArrayList<String>();
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    private String UserId;
    private TextView tt,TNif,TDate,TTime,TName,TSL,TEL,TPrice,TTAO,TSupp,TNote;
    private Invoice user;
    private Button Show,Print;


    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    // needed for communication to bluetooth device / network
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinvoice);
        intwidget();
        calllistner();

        Store=getIntent().getExtras().getString("Store");

        if(Store.equals("MAnual")){
           FileOutputStream fos = null;
           // add-write text into file
           try {
               Intent intent=getIntent();
               user=(Invoice) intent.getExtras().getSerializable("user");
               String filename="Invoice"+String.valueOf(user.InvoiceID)+".txt";
               File path =new File(getFilesDir().getPath(),"InvoiceData");
               if(!path.exists()){
                   path.mkdir();
               }
               File directory = new File(path,filename);

               Log.d("DilesFlies",directory.getAbsolutePath());
               FileOutputStream fileout=new FileOutputStream(directory);
               OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
               outputWriter.write(user.getNIF()+":NIF"+"\n");
               outputWriter.write(user.getPrice()+":Price"+"\n");
               outputWriter.write(user.getTollsAndOthers()+":TollsAndOthers"+"\n");
               outputWriter.write(user.getNotes()+":notes"+"\n");
               outputWriter.write(String.valueOf(user.getSupplements())+":supplements"+"\n");
               outputWriter.write(String.valueOf(user.getDay())+":Day"+"\n");
               outputWriter.write(String.valueOf(user.getMounth())+":Mounth"+"\n");
               outputWriter.write(String.valueOf(user.getYear())+":Year"+"\n");
               outputWriter.write(String.valueOf(user.getTime())+":time"+"\n");
               outputWriter.write(user.getTotal()+":Total"+"\n");
               outputWriter.write(user.getName()+":Name"+"\n");
               outputWriter.write(user.getStartLocation() +":StartLocation"+"\n");
               outputWriter.write(user.getEndLocation()+":EndLocation"+"\n");
               outputWriter.write(String.valueOf(user.getInvoiceID())+":InvoiceID"+"\n");

               outputWriter.close();

               SharedPreferences.Editor editor = getSharedPreferences("Project", MODE_PRIVATE).edit();
               editor.putString("LAstInvoice", filename);
               editor.apply();

               //TinyDB tinydb = new TinyDB(this);
               //Toast.makeText(Showinvoice.this, String.valueOf(user.getInvoiceID()), Toast.LENGTH_SHORT).show();

               //display file saved message
               TName.setText(user.getName());
               //String nif=snapshot.child("NIF").getValue(String.class);
               TNif.setText(user.getNIF());
               //String day=snapshot.child("Day").getValue(String.class);
               //String week=snapshot.child("Mounth").getValue(String.class);
               //String year=snapshot.child("Year").getValue(String.class);
               TDate.setText(user.getDay() + "/" + user.getMounth() + "/" + user.getYear());
               //String startloc=snapshot.child("StartLocation").getValue(String.class);
               TSL.setText(user.getStartLocation());
               //String endLoc=snapshot.child("EndLocation").getValue(String.class);
               TEL.setText(user.getEndLocation());
               //String pric=snapshot.child("price").getValue(String.class);
               TPrice.setText(user.getPrice());
               // String tollsand=snapshot.child("TollsAndOthers").getValue(String.class);
               TTAO.setText(user.getTollsAndOthers());
               //String supplem=snapshot.child("supplements").getValue(String.class);
               TSupp.setText(user.getSupplements());
               //String not=snapshot.child("notes").getValue(String.class);
               TNote.setText(user.getNotes());
               //String tim=snapshot.child("time").getValue(String.class);
               TTime.setText(user.getTime());


           } catch (Exception e) {
               Toast.makeText(getBaseContext(), e.getMessage(),
                       Toast.LENGTH_SHORT).show();
           }

       }
       else if(Store.equals("Simplified")){
            FileOutputStream fos = null;
            // add-write text into file
            try {
                Intent intent=getIntent();
                user=(Invoice) intent.getExtras().getSerializable("user");
                String filename="Invoice"+String.valueOf(user.InvoiceID)+".txt";
                File path =new File(getFilesDir().getPath(),"InvoiceData");
                if(!path.exists()){
                    path.mkdir();
                }
                File directory = new File(path,filename);

                Log.d("DilesFlies",directory.getAbsolutePath());
                FileOutputStream fileout=new FileOutputStream(directory);
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                outputWriter.write(user.getNIF()+":NIF"+"\n");
                outputWriter.write(user.getPrice()+":Price"+"\n");
                outputWriter.write(user.getTollsAndOthers()+":TollsAndOthers"+"\n");
                outputWriter.write(user.getNotes()+":notes"+"\n");
                outputWriter.write(String.valueOf(user.getSupplements())+":supplements"+"\n");
                outputWriter.write(String.valueOf(user.getDay())+":Day"+"\n");
                outputWriter.write(String.valueOf(user.getMounth())+":Mounth"+"\n");
                outputWriter.write(String.valueOf(user.getYear())+":Year"+"\n");
                outputWriter.write(String.valueOf(user.getTime())+":time"+"\n");
                outputWriter.write(user.getTotal()+":Total"+"\n");
                outputWriter.write(user.getName()+":Name"+"\n");
                outputWriter.write(user.getStartLocation() +":StartLocation"+"\n");
                outputWriter.write(user.getEndLocation()+":EndLocation"+"\n");
                outputWriter.write(String.valueOf(user.getInvoiceID())+":InvoiceID"+"\n");
                outputWriter.close();

                //display file saved message

                SharedPreferences.Editor editor = getSharedPreferences("Project", MODE_PRIVATE).edit();
                editor.putString("LAstInvoice", filename);
                editor.apply();
                //Toast.makeText(Showinvoice.this, String.valueOf(user.getInvoiceID()), Toast.LENGTH_SHORT).show();
                TName.setText(user.getName());
                //String nif=snapshot.child("NIF").getValue(String.class);
                TNif.setText(user.getNIF());
                //String day=snapshot.child("Day").getValue(String.class);
                //String week=snapshot.child("Mounth").getValue(String.class);
                //String year=snapshot.child("Year").getValue(String.class);
                TDate.setText(user.getDay() + "/" + user.getMounth() + "/" + user.getYear());
                //String startloc=snapshot.child("StartLocation").getValue(String.class);
                TSL.setText(user.getStartLocation());
                //String endLoc=snapshot.child("EndLocation").getValue(String.class);
                TEL.setText(user.getEndLocation());
                //String pric=snapshot.child("price").getValue(String.class);
                TPrice.setText(user.getPrice());
                // String tollsand=snapshot.child("TollsAndOthers").getValue(String.class);
                TTAO.setText(user.getTollsAndOthers());
                //String supplem=snapshot.child("supplements").getValue(String.class);
                TSupp.setText(user.getSupplements());
                //String not=snapshot.child("notes").getValue(String.class);
                TNote.setText(user.getNotes());
                //String tim=snapshot.child("time").getValue(String.class);
                TTime.setText(user.getTime());


            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "File created Failed",
                        Toast.LENGTH_SHORT).show();
            }
       }
       else if(Store.equals("Print"))
        {
            //"No name defined" is the default value.
            //tt=findViewById(R.id.textView11);
            key1 = getIntent().getExtras().getString("Filename");
            //UserId=firebaseAuth.getCurrentUser().getUid();
            //Toast.makeText(Showinvoice.this,key1,Toast.LENGTH_LONG).show();
            //tt.setText(key1);
            Query reference;
            try {
                /** reference = FirebaseDatabase.getInstance().getReference("Invoice").child(UserId).child(key1);
                 reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                String name=snapshot.child("Name").getValue(String.class);
                TName.setText(name);
                String nif=snapshot.child("NIF").getValue(String.class);
                TNif.setText(nif);
                String day=snapshot.child("Day").getValue(String.class);
                String week=snapshot.child("Mounth").getValue(String.class);
                String year=snapshot.child("Year").getValue(String.class);
                TDate.setText(day+"/"+week+"/"+year);
                String startloc=snapshot.child("StartLocation").getValue(String.class);
                TSL.setText(startloc);
                String endLoc=snapshot.child("EndLocation").getValue(String.class);
                TEL.setText(endLoc);
                String pric=snapshot.child("price").getValue(String.class);
                TPrice.setText(pric);
                String tollsand=snapshot.child("TollsAndOthers").getValue(String.class);
                TTAO.setText(tollsand);
                String supplem=snapshot.child("supplements").getValue(String.class);
                TSupp.setText(supplem);
                String not=snapshot.child("notes").getValue(String.class);
                TNote.setText(not);
                String tim=snapshot.child("time").getValue(String.class);
                TTime.setText(tim);




                }
                SharedPreferences.Editor editor = getSharedPreferences("Invoice", MODE_PRIVATE).edit();
                editor.putString("LastInvoice", key1);
                editor.putString("Key",null);
                editor.apply();

                }

                @Override public void onCancelled(@NonNull DatabaseError error) {

                }

                });
                 **/
                // Toast.makeText(Showinvoice.this, key1, Toast.LENGTH_SHORT).show();
                File directory = new File(String.valueOf(this.getFilesDir().getPath())+"/InvoiceData");
                StringBuilder text = new StringBuilder();
                try {


                    File currentData=new File(directory,key1);
                    FileInputStream instream = new FileInputStream(currentData);
                    if (instream != null) {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line, line1 = "";
                        int len = 0;


                        try {
                            while ((line = buffreader.readLine()) != null) {
                                //line1+=line;
                                String[] a = line.split(":");
                                data.add(String.valueOf(a[0].trim()));

                            }
                            TName.setText(data.get(10));
                            //String nif=snapshot.child("NIF").getValue(String.class);
                            TNif.setText(data.get(0));
                            //String day=snapshot.child("Day").getValue(String.class);
                            //String week=snapshot.child("Mounth").getValue(String.class);
                            //String year=snapshot.child("Year").getValue(String.class);
                            TDate.setText(data.get(5) + "/" + data.get(6) + "/" + data.get(7));
                            //String startloc=snapshot.child("StartLocation").getValue(String.class);
                            TSL.setText(data.get(11));
                            //String endLoc=snapshot.child("EndLocation").getValue(String.class);
                            TEL.setText(data.get(12));
                            //String pric=snapshot.child("price").getValue(String.class);
                            TPrice.setText(data.get(1));
                            // String tollsand=snapshot.child("TollsAndOthers").getValue(String.class);
                            TTAO.setText(data.get(2));
                            //String supplem=snapshot.child("supplements").getValue(String.class);
                            TSupp.setText(data.get(4));
                            //String not=snapshot.child("notes").getValue(String.class);
                            TNote.setText(data.get(3));
                            //String tim=snapshot.child("time").getValue(String.class);
                            TTime.setText(data.get(8));

                            //Toast.makeText(Showinvoice.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(Showinvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(Showinvoice.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(Showinvoice.this, "check your connection", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void calllistner() {
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Showinvoice.this,StaticHolder.mmDevice.getName(),Toast.LENGTH_SHORT).show();
                try {
                    openBT();
                } catch (IOException e) {
                    Toast.makeText(Showinvoice.this,"Connection not found",Toast.LENGTH_SHORT).show();
                }
                try {
                    sendData();
                } catch (IOException e) {
                    Toast.makeText(Showinvoice.this,"Data Sending failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void sendData() throws IOException {
        try {

            // the text typed by the user
            String msg = "myTextbox.getText().toString()";
            msg += "\n";

            mmOutputStream.write(msg.getBytes());

            Log.d("Datata","sended");
            // tell the user data were sent
            //myLabel.setText("Data sent.");
            Toast.makeText(Showinvoice.this,"here",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
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
        fstore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        TNif=findViewById(R.id.txtShowNif);
        TDate=findViewById(R.id.txtShowDate);
        TTime=findViewById(R.id.txtShowtime);
        TName=findViewById(R.id.txtShowName);
        TSL=findViewById(R.id.txtShowSL);
        TEL=findViewById(R.id.txtShowEL);
        TPrice=findViewById(R.id.txtShowPrice);
        TTAO=findViewById(R.id.txtShowTAO);
        TSupp=findViewById(R.id.txtShowSup);
        TNote=findViewById(R.id.txtShowNote);
        Show=findViewById(R.id.btnShowCancel);
        Print=findViewById(R.id.btnShowPrint);
    }
}