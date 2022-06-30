package com.example.newtravel.Settings;

import static com.example.newtravel.R.drawable.circularshap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.Menu;
import com.example.newtravel.R;
import com.example.newtravel.SettingMenu;

public class SecurityPin extends AppCompatActivity {
    private String def;
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bok;
    private TextView head,h1,h2,h3,h4;
    private int TotalSize=4,currentSize=0,index=0;
    int[] pass=new int[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pin);
    
        intwidget();
        def=getIntent().getExtras().getString("Type");

        if(def.equals("New")){

           callListner();
           head.setText("New Pin");

        }
        if(def.equals("Change")){

            callListner();
            head.setText("Insert Pin");

        }
        else if(def.equals("Confirm")){
            callListner();
            head.setText("Confirm Pin");

        }
        else if(def.equals("Login")){
            callListner();
            head.setText("Login");
        }

    }

    private void showText(int u){
       if(u==1){
           h1.setBackground(getResources().getDrawable(circularshap));
       }
       else if(u==2){
           h1.setBackground(getResources().getDrawable(circularshap));
           h2.setBackground(getResources().getDrawable(circularshap));
       }
       else if(u==3){
           h1.setBackground(getResources().getDrawable(circularshap));
           h2.setBackground(getResources().getDrawable(circularshap));
           h3.setBackground(getResources().getDrawable(circularshap));
       }
       else if(u==4){
           h1.setBackground(getResources().getDrawable(circularshap));
           h2.setBackground(getResources().getDrawable(circularshap));
           h3.setBackground(getResources().getDrawable(circularshap));
           h4.setBackground(getResources().getDrawable(circularshap));
       }
    }
    private void callListner() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=1;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=2;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=3;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=4;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=5;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=6;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=7;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=8;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=9;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSize<4){
                    pass[index]=0;
                    index++;
                    currentSize++;
                    showText(currentSize);
                }
            }
        });

        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<pass.length;i++){
                    Log.d("datahereis",String.valueOf(pass[i]));
                }
                if(def.equals("New")){
                    String passw= convertString(pass);

                    SharedPreferences.Editor edit= getSharedPreferences("Project",MODE_PRIVATE).edit();
                    edit.putString("Password",passw);
                    edit.apply();
                    Toast.makeText(SecurityPin.this, "Password Created", Toast.LENGTH_SHORT).show();
                }
                else if(def.equals("Change")){

                    String passw= convertString(pass);

                    if(!TextUtils.isEmpty(passw)){
                         SharedPreferences.Editor edit= getSharedPreferences("Project",MODE_PRIVATE).edit();
                        edit.putString("ConfirmPassword",passw);
                        edit.apply();

                        Log.d("Checkingpass","correnct");

                        Intent i=new Intent(SecurityPin.this,SecurityPin.class);
                        i.putExtra("Type","Confirm");
                        startActivity(i);
                        finish();
                    }

                }
                else if(def.equals("Confirm")){
                    SharedPreferences prefs = getSharedPreferences("Project", MODE_PRIVATE);
                    String name = prefs.getString("ConfirmPassword", null);//"No name defined" is the default value.

                    String passw= convertString(pass);

                    //Log.d("Datata",name+"  "+passw);
                    if(passw.equals(name)){
                        SharedPreferences.Editor edit= getSharedPreferences("Project",MODE_PRIVATE).edit();
                        edit.putString("Password",passw);
                        edit.apply();

                        Toast.makeText(SecurityPin.this, "Changed Successfully", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(SecurityPin.this, SettingMenu.class);
                        startActivity(i);
                        finish();
                    }
                    else{

                        Toast.makeText(SecurityPin.this, "Password Not Matched", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(SecurityPin.this, SettingMenu.class);
                        startActivity(i);
                        finish();
                    }

                }
                else if(def.equals("Login")){
                    SharedPreferences prefs = getSharedPreferences("Project", MODE_PRIVATE);
                    String name = prefs.getString("Password", null);//"No name defined" is the default value.

                    String passw= convertString(pass);

                    //Log.d("Datata",name+"  "+passw);
                    if(passw.equals(name)){

                        Intent i=new Intent(SecurityPin.this, Menu.class);
                        i.putExtra("LoggedIn","True");
                        startActivity(i);
                        finish();
                    }
                    else{

                        Toast.makeText(SecurityPin.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(SecurityPin.this, SecurityPin.class);
                        i.putExtra("Type","Login");
                        startActivity(i);
                        finish();

                    }

                }
            }
        });
    }

    private void intwidget() {
        b1=findViewById(R.id.btnPinsec1);
        b2=findViewById(R.id.btnPinsec2);
        b3=findViewById(R.id.btnPinsec3);
        b4=findViewById(R.id.btnPinsec4);
        b5=findViewById(R.id.btnPinsec5);
        b6=findViewById(R.id.btnPinsec6);
        b7=findViewById(R.id.btnPinsec7);
        b8=findViewById(R.id.btnPinsec8);
        b9=findViewById(R.id.btnPinsec9);
        b0=findViewById(R.id.btnPinsec0);
        bok=findViewById(R.id.btnPinsecOk);

        head=findViewById(R.id.textViewSecurityM);
        h1=findViewById(R.id.Passwaord1);
        h2=findViewById(R.id.passwaord);
        h3=findViewById(R.id.passwaord2);
        h4=findViewById(R.id.passwaord3);

    }
    private String convertString(int[] a){
        String pass="";
        for(int i=0;i<a.length;i++){
            pass=pass+String.valueOf(a[i]);
        }
        return pass;
    }
}