package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.Static.StaticHolder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Calendar;

public class MAnualReciept extends AppCompatActivity {
    private TextInputLayout Nif,Price,Tolls,Notes,Supp,EndL,StartL,Name;
    private ImageView img1,img2,img3;
    double Total=0,PriceTotal,TollsTotal,Suppp=0,checked=0;
    private TextView tt;
    private Button Add,print;
    String key;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    String Recent="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplified_invoice);
        intwidget();
        callListner();
    }

    private void callListner() {

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ni = Nif.getEditText().getText().toString();
                String Pri = Price.getEditText().getText().toString();
                String tol = Tolls.getEditText().getText().toString();
                String Not = Notes.getEditText().getText().toString();
                String ENDLocation=EndL.getEditText().getText().toString();
                String STARTLOCATIOm=StartL.getEditText().getText().toString();
                String Nam=Name.getEditText().getText().toString();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DATE);

                String sec=String .valueOf(calendar.get(Calendar.SECOND));
                String minute=String .valueOf(calendar.get(Calendar.MINUTE));
                String hours=String .valueOf(calendar.get(Calendar.HOUR));
                String timmecreated=hours+"/"+minute+"/"+sec;

                if (TextUtils.isEmpty(Ni)) {
                    Nif.setError("NIF cannot be empty");
                }else if (TextUtils.isEmpty(ENDLocation)) {
                    EndL.setError("End Location cannot be empty");
                }
                else if (TextUtils.isEmpty(STARTLOCATIOm)) {
                    StartL.setError("Start Location cannot be empty");
                }
                else if (TextUtils.isEmpty(Nam)) {
                    Name.setError("Name Location cannot be empty");
                }
                else if (TextUtils.isEmpty(Pri)) {
                    Price.setError("Price cannot be empty");
                } else if (TextUtils.isEmpty(tol)) {
                    Tolls.setError("Tolls and Others cannot be empty");
                } else if (TextUtils.isEmpty(Not)) {
                    Notes.setError("Note cannot be empty");
                } else {
                    try {
                        /**String UserID = firebaseAuth.getCurrentUser().getUid();
                        String mGroupId = mDatabase.push().getKey();
                        key=mGroupId;
                        //Toast.makeText(MAnualReciept.this,mGroupId,Toast.LENGTH_LONG).show();
                        mDatabase.child("Invoice").child(UserID).child(key).setValue(user);
                        Toast.makeText(MAnualReciept.this, "Success", Toast.LENGTH_LONG).show();
                        checked=1;
                        SharedPreferences.Editor editor = getSharedPreferences("Invoice", MODE_PRIVATE).edit();
                        editor.putString("LastInvoice", key);

                        editor.apply();
                         **/
                        Invoice user = new Invoice(Nam, Ni, STARTLOCATIOm, ENDLocation, Pri, String.valueOf(tol), String.valueOf(Suppp), Not, String.valueOf(Total), String.valueOf(day), String.valueOf(month), String.valueOf(year),timmecreated);
                        checked=1;
                        StaticHolder.lastData=user.InvoiceID;
                        //display file saved message
                        Intent i=new Intent(MAnualReciept.this,Showinvoice.class);
                        i.putExtra("Store","MAnual");
                        i.putExtra("user", user);
                        startActivity(i);
                        /**FileOutputStream fos = null;
                        // add-write text into file
                        try {
                            Invoice user = new Invoice(Nam, Ni, STARTLOCATIOm, ENDLocation, Pri, String.valueOf(tol), String.valueOf(Suppp), Not, String.valueOf(Total), String.valueOf(day), String.valueOf(month), String.valueOf(year),timmecreated);
                            String filename="Invoice"+String.valueOf(user.InvoiceID)+".txt";
                            FileOutputStream fileout=openFileOutput(filename, MODE_PRIVATE);
                            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                            outputWriter.write(Ni+":NIF"+"\n");
                            outputWriter.write(Pri+":Price"+"\n");
                            outputWriter.write(tol+":TollsAndOthers"+"\n");
                            outputWriter.write(Not+":notes"+"\n");
                            outputWriter.write(String.valueOf(Suppp)+":supplements"+"\n");
                            outputWriter.write(String.valueOf(day)+":Day"+"\n");
                            outputWriter.write(String.valueOf(month)+":Mounth"+"\n");
                            outputWriter.write(String.valueOf(year)+":Year"+"\n");
                            outputWriter.write(String.valueOf(timmecreated)+":time"+"\n");
                            outputWriter.write(Total+":Total"+"\n");
                            outputWriter.write(Nam+":Name"+"\n");
                            outputWriter.write(STARTLOCATIOm +":StartLocation"+"\n");
                            outputWriter.write(ENDLocation+":EndLocation"+"\n");
                            outputWriter.write(String.valueOf(user.InvoiceID)+":InvoiceID"+"\n");

                            outputWriter.close();
                            Recent=filename;
                            checked=1;
                            StaticHolder.lastData=user.InvoiceID;
                            //display file saved message
                            Intent i=new Intent(MAnualReciept.this,Showinvoice.class);
                            i.putExtra("Filename",Recent);
                            startActivity(i);
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), "File created Failed",
                                    Toast.LENGTH_SHORT).show();
                        }**/

                    } catch (Exception e) {
                        Toast.makeText(MAnualReciept.this, "Failed! created file", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
        Notes.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Total=Suppp+PriceTotal+TollsTotal;
                Total = (int)(Math.round(Total * 100))/100.0;
                tt.setText((String.valueOf(Total)));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Price.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(SimplifiedInvoice.this,"sdaf",Toast.LENGTH_SHORT).show();
                String Pri=Price.getEditText().getText().toString();
                //Total=Total+Integer.parseInt(Pri);
                //tt.setText(String.valueOf(Total));
                PriceTotal=Double.valueOf(Pri);
            }
        });


        Tolls.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String Noi=Tolls.getEditText().getText().toString();
                //Total=Total+Integer.parseInt(Pri);
                //tt.setText(String.valueOf(Total));
                TollsTotal=Double.valueOf(Noi);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suppp=Suppp+2.5;
                Toast.makeText(MAnualReciept.this,"Supplementry Added",Toast.LENGTH_SHORT).show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suppp=Suppp+2.5;
                Toast.makeText(MAnualReciept.this,"Supplementry Added",Toast.LENGTH_SHORT).show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Suppp=Suppp+1.5;
                Toast.makeText(MAnualReciept.this,"Supplementry Added",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void intwidget() {
        Nif=findViewById(R.id.etManNIF);
        Price=findViewById(R.id.etManPrice);
        Tolls=findViewById(R.id.etMAnTools);
        Notes=findViewById(R.id.etSimNote);
        EndL=findViewById(R.id.etManEnd);
        StartL=findViewById(R.id.etStarLoc);
        Name=findViewById(R.id.etManName);
         img1=findViewById(R.id.imgSimpDog);
         img2=findViewById(R.id.imgSimBag);
         img3=findViewById(R.id.imgSimPhone);
         Add=findViewById(R.id.btnRecPrint);
        fstore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tt=findViewById(R.id.MAntextGetTotal);
        print=findViewById(R.id.btnRecPrint);
    }
}