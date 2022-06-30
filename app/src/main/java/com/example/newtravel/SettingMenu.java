package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newtravel.Printer.FoundPrinter;
import com.example.newtravel.Settings.AddSecurity;
import com.example.newtravel.Settings.ChangePin;

public class SettingMenu extends AppCompatActivity {
    private Button printer,Uesr,Invoice,Secu,accountamnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);

        intwidget();
        calllistner();
    }

    private void calllistner() {
        printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingMenu.this, FoundPrinter.class);
                startActivity(i);
            }
        });

        Uesr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingMenu.this, UserSetting.class);
                startActivity(i);
            }
        });

        Invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingMenu.this, InvoiceSetting.class);
                startActivity(i);
            }
        });

        Secu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(SecurityPin.this, passw, Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getSharedPreferences("Project", MODE_PRIVATE);
                String name = prefs.getString("Password", null);//"No name defined" is the default value.
               // Log.d("passwordchecl",name);

                if(TextUtils.isEmpty(name)){
                    Intent i=new Intent(SettingMenu.this, AddSecurity.class);
                    startActivity(i);
                }
                else{
                 //   Log.d("passwordchecl","here");
                    Intent i=new Intent(SettingMenu.this, ChangePin.class);
                    startActivity(i);
                }

            }
        });

        accountamnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingMenu.this, Accountant.class);
                startActivity(i);
            }
        });
    }

    private void intwidget() {
        Uesr=findViewById(R.id.btnSetUser);
        printer=findViewById(R.id.btnSetPrint);
        Invoice=findViewById(R.id.btnSetInv);
        Secu=findViewById(R.id.btnSetSec);
        accountamnt=findViewById(R.id.btnSetAcc);
    }


}