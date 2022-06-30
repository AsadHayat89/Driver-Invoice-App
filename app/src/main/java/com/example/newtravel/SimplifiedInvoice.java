package com.example.newtravel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class SimplifiedInvoice extends AppCompatActivity {
    private TextInputLayout Nif,Price,Tolls,Notes,Supp;
    private ImageView img1,img2,img3;
    double Total=0,PriceTotal,TollsTotal,Suppp=0;
    private TextView tt;
    private Button Add,print;
    String key;
    int checked=0;
    String Recent="";
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_reciept);
        intiwidget();
        callListner();
    }

    private void intiwidget() {
        Nif=findViewById(R.id.etSimNIF);
        Price=findViewById(R.id.etSetingUserName);
        Tolls=findViewById(R.id.etSimTools);
        Notes=findViewById(R.id.etSimNote);
        img1=findViewById(R.id.imageView3);
        img2=findViewById(R.id.imageView4);
        img3=findViewById(R.id.imageView5);
        Add=findViewById(R.id.btnAccountantBtn);
        tt=findViewById(R.id.textGetTotal);
        fstore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void callListner() {

        Add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String Ni = Nif.getEditText().getText().toString();
                String Pri = Price.getEditText().getText().toString();
                String tol = Tolls.getEditText().getText().toString();
                String Not = Notes.getEditText().getText().toString();

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
                } else if (TextUtils.isEmpty(Pri)) {
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
                        Toast.makeText(SimplifiedInvoice.this,mGroupId,Toast.LENGTH_LONG).show();
                        mDatabase.child("Invoice").child(UserID).child(key).setValue(user);
                        Toast.makeText(SimplifiedInvoice.this, "Success", Toast.LENGTH_LONG).show();
                        checked=1;
                        SharedPreferences.Editor editor = getSharedPreferences("Invoice", MODE_PRIVATE).edit();
                        editor.putString("LastInvoice", key);

                        editor.apply();**/
                        Invoice user = new Invoice("", Ni, "", "", Pri, String.valueOf(tol), String.valueOf(Suppp), Not, String.valueOf(Total), String.valueOf(day), String.valueOf(month), String.valueOf(year),timmecreated);
                        Intent i=new Intent(SimplifiedInvoice.this,Showinvoice.class);
                        i.putExtra("Store","Simplified");
                        i.putExtra("user",user);
                        startActivity(i);
                        /**FileOutputStream fos = null;
                        // add-write text into file
                        try {

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
                            outputWriter.write(""+":Name"+"\n");
                            outputWriter.write("" +":StartLocation"+"\n");
                            outputWriter.write(""+":EndLocation"+"\n");
                            outputWriter.write(String.valueOf(user.InvoiceID)+":InvoiceID"+"\n");
                            outputWriter.close();
                            checked=1;
                            //display file saved message
                            Toast.makeText(getBaseContext(), "File saved successfully!",
                                    Toast.LENGTH_SHORT).show();
                            Recent=filename;
                            StaticHolder.lastData=user.InvoiceID;

                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), "File created Failed",
                                    Toast.LENGTH_SHORT).show();
                        }**/
                    } catch (Exception e) {
                        Toast.makeText(SimplifiedInvoice.this, "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
        Notes.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //File sdcard = Environment.getExternalStorageDirectory();
                //Toast.makeText(SimplifiedInvoice.this,sdcard.toString(), Toast.LENGTH_SHORT).show();

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
        Toast.makeText(SimplifiedInvoice.this,"Supplementry Added",Toast.LENGTH_SHORT).show();

    }
});
img2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Suppp=Suppp+2.5;
        Toast.makeText(SimplifiedInvoice.this,"Supplementry Added",Toast.LENGTH_SHORT).show();
    }
});
img3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Suppp=Suppp+1.5;
        Toast.makeText(SimplifiedInvoice.this,"Supplementry Added",Toast.LENGTH_SHORT).show();
    }
});
    }

}