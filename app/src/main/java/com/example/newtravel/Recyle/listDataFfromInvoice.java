package com.example.newtravel.Recyle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.newtravel.R;
import com.example.newtravel.Showinvoice;
import com.example.newtravel.Static.StaticHolder;
import com.example.newtravel.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class listDataFfromInvoice extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    private ArrayList<Items> itemsList=new ArrayList<>();
    int io;
    private File[] files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_ffrom_invoice);

        intwidget();

        //Log.d("Pathvalue",this.getFilesDir().getPath());
        File directory = new File(String.valueOf(this.getFilesDir().getPath())+"/InvoiceData");
        files = directory.listFiles();
        for(int i=0;i<files.length;i++){
            Log.d("dataFiles",files[i].getName());
        }

        String key1 = getIntent().getExtras().getString("type");

        if (key1.equals("list")) {
            //Toast.makeText(listDataFfromInvoice.this,key1,Toast.LENGTH_SHORT).show();
            //String UserId = firebaseAuth.getCurrentUser().getUid();



            Query reference;
            try {
                /**reference = FirebaseDatabase.getInstance().getReference("Invoice").child(UserId);
                 reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot Snap : snapshot.getChildren()) {
                //Toast.makeText(listDataFfromInvoice.this,"Check your connection",Toast.LENGTH_SHORT).show();
                String key=Snap.getKey();
                String name=snapshot.child(key).child("Name").getValue(String.class);
                String time=snapshot.child(key).child("time").getValue(String.class);
                String toal=snapshot.child(key).child("Total").getValue(String.class);
                String day=snapshot.child(key).child("Day").getValue(String.class);
                String month=snapshot.child(key).child("Mounth").getValue(String.class);
                String year=snapshot.child(key).child("Year").getValue(String.class);
                if(TextUtils.isEmpty(name)){
                name="not available";
                }
                else if(TextUtils.isEmpty(time)){
                time="not available";
                }
                else if(TextUtils.isEmpty(toal)){
                toal="not available";
                }
                else if(TextUtils.isEmpty(day)){
                day="not available";
                }
                itemsList.add(new Items(day+"/"+month+"/"+year,time,name,toal,key));
                io++;
                //Toast.makeText(listDataFfromInvoice.this,"Check your connection agin",Toast.LENGTH_SHORT).show();
                //String key=Snap.getKey();
                //Toast.makeText(listDataFfromInvoice.this,snapshot.child(key).child("price").getValue(String.class),Toast.LENGTH_SHORT).show();
                }
                if(itemsList.isEmpty()){
                Toast.makeText(listDataFfromInvoice.this,"No such result found",Toast.LENGTH_LONG);
                finish();
                }
                //Toast.makeText(listDataFfromInvoice.this,String .valueOf(itemsList.size()),Toast.LENGTH_SHORT).show();
                RecyclerviewItemAdapter recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);

                RecyclerView recyclerView=findViewById(R.id.recycleView);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewItemAdapter);

                }

                @Override public void onCancelled(@NonNull DatabaseError error) {

                }
                });**/
                try {

                    for (int i = 0; i < files.length; i++) {
                        StringBuilder text = new StringBuilder();

                        String name = files[i].getName();
                        //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                        //Log.d("filenmae",name);
                        File currentData=new File(directory,name);
                        FileInputStream instream = new FileInputStream(currentData);
                        if (instream != null) {
                            InputStreamReader inputreader = new InputStreamReader(instream);
                            BufferedReader buffreader = new BufferedReader(inputreader);
                            String line, line1 = "";

                            ArrayList<String> data = new ArrayList<>();
                            try {
                                while ((line = buffreader.readLine()) != null) {
                                    //line1+=line;
                                    String[] a = line.split(":");
                                    data.add(String.valueOf(a[0].trim()));

                                }
                                itemsList.add(new Items(data.get(5) + "/" + data.get(6) + "/" + data.get(7), data.get(8), data.get(10), data.get(9), "Invoice" + data.get(13) + ".txt"));
                            } catch (Exception e) {
                                Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                    //Toast.makeText(listDataFfromInvoice.this, String.valueOf(itemsList.size()), Toast.LENGTH_SHORT).show();
                    RecyclerviewItemAdapter recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);

                    RecyclerView recyclerView = findViewById(R.id.recycleView);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(recyclerviewItemAdapter);
                    //Toast.makeText(Showinvoice.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                Toast.makeText(listDataFfromInvoice.this, "Check your connection", Toast.LENGTH_SHORT).show();
            }
        } else if (key1.equals("Byname")) {
            String sername = getIntent().getExtras().getString("SearchName");
            try {

                for (int i = 0; i < files.length; i++) {
                    StringBuilder text = new StringBuilder();
                    String name = files[i].getName();
                    //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                    File currentData=new File(directory,name);
                    FileInputStream instream = new FileInputStream(currentData);
                    if (instream != null) {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line, line1 = "";

                        ArrayList<String> data = new ArrayList<>();
                        try {
                            while ((line = buffreader.readLine()) != null) {
                                //line1+=line;
                                String[] a = line.split(":");
                                data.add(String.valueOf(a[0].trim()));

                            }
                            if (sername.equals(data.get(10))) {
                                itemsList.add(new Items(data.get(5) + "/" + data.get(6) + "/" + data.get(7), data.get(8), data.get(10), data.get(9), "Invoice" + data.get(13) + ".txt"));
                            }

                        } catch (Exception e) {
                            Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
                //Toast.makeText(listDataFfromInvoice.this, String.valueOf(itemsList.size()), Toast.LENGTH_SHORT).show();
                RecyclerviewItemAdapter recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);

                RecyclerView recyclerView = findViewById(R.id.recycleView);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewItemAdapter);
                //Toast.makeText(Showinvoice.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
            }


        } else if (key1.equals("SearchByDate")) {
            String day1 = getIntent().getExtras().getString("day");
            String Year1 = getIntent().getExtras().getString("Year");
            String Month1 = getIntent().getExtras().getString("Month");
            try {

                for (int i = 0; i < files.length; i++) {
                    StringBuilder text = new StringBuilder();
                    String name = files[i].getName();
                    //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                    File currentData=new File(directory,name);
                    FileInputStream instream = new FileInputStream(currentData);
                    if (instream != null) {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line, line1 = "";

                        ArrayList<String> data = new ArrayList<>();
                        try {
                            while ((line = buffreader.readLine()) != null) {
                                //line1+=line;
                                String[] a = line.split(":");
                                data.add(String.valueOf(a[0].trim()));

                            }
                            if (day1.equals(data.get(5)) && Month1.equals(data.get(6)) && Year1.equals(data.get(7))) {
                                itemsList.add(new Items(data.get(5) + "/" + data.get(6) + "/" + data.get(7), data.get(8), data.get(10), data.get(9), "Invoice" + data.get(13) + ".txt"));
                            }

                        } catch (Exception e) {
                            Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
                //Toast.makeText(listDataFfromInvoice.this, String.valueOf(itemsList.size()), Toast.LENGTH_SHORT).show();
                RecyclerviewItemAdapter recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);

                RecyclerView recyclerView = findViewById(R.id.recycleView);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewItemAdapter);
                //Toast.makeText(Showinvoice.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        } else if (key1.equals("SearchByMonth")) {
            String Year1 = getIntent().getExtras().getString("Year");
            String Month1 = getIntent().getExtras().getString("Month");
            try {

                for (int i = 0; i < files.length; i++) {
                    StringBuilder text = new StringBuilder();
                    String name = files[i].getName();
                    //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                    File currentData=new File(directory,name);
                    FileInputStream instream = new FileInputStream(currentData);
                    if (instream != null) {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line, line1 = "";

                        ArrayList<String> data = new ArrayList<>();
                        try {
                            while ((line = buffreader.readLine()) != null) {
                                //line1+=line;
                                String[] a = line.split(":");
                                data.add(String.valueOf(a[0].trim()));

                            }
                            if (Month1.equals(data.get(6)) && Year1.equals(data.get(7))) {
                                itemsList.add(new Items(data.get(5) + "/" + data.get(6) + "/" + data.get(7), data.get(8), data.get(10), data.get(9), "Invoice" + data.get(13) + ".txt"));
                            }

                        } catch (Exception e) {
                            Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
                //Toast.makeText(listDataFfromInvoice.this, String.valueOf(itemsList.size()), Toast.LENGTH_SHORT).show();
                RecyclerviewItemAdapter recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);

                RecyclerView recyclerView = findViewById(R.id.recycleView);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewItemAdapter);
                //Toast.makeText(Showinvoice.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (key1.equals("SearchByYear")) {
            String Year1 = getIntent().getExtras().getString("Year");
            try {

                for (int i = 0; i < files.length; i++) {
                    StringBuilder text = new StringBuilder();
                    String name = files[i].getName();
                    //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                    File currentData=new File(directory,name);
                    FileInputStream instream = new FileInputStream(currentData);
                    if (instream != null) {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line, line1 = "";

                        ArrayList<String> data = new ArrayList<>();
                        try {
                            while ((line = buffreader.readLine()) != null) {
                                //line1+=line;
                                String[] a = line.split(":");
                                data.add(String.valueOf(a[0].trim()));

                            }
                            if (Year1.equals(data.get(7))) {
                                itemsList.add(new Items(data.get(5) + "/" + data.get(6) + "/" + data.get(7), data.get(8), data.get(10), data.get(9), "Invoice" + data.get(13) + ".txt"));
                            }

                        } catch (Exception e) {
                            Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
                //Toast.makeText(listDataFfromInvoice.this, String.valueOf(itemsList.size()), Toast.LENGTH_SHORT).show();
                RecyclerviewItemAdapter recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);

                RecyclerView recyclerView = findViewById(R.id.recycleView);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewItemAdapter);
                //Toast.makeText(Showinvoice.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(listDataFfromInvoice.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }
    private void intwidget() {
        fstore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}