package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class UserSetting extends AppCompatActivity {

    private TextInputLayout name,address,phone,Nif,email;
    private Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        intwidget();
        callListner();
    }

    private void callListner() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void intwidget() {
        name=findViewById(R.id.etSetingUserName);
        address=findViewById(R.id.etSetingUserAdress);
        phone=findViewById(R.id.etSetingUserPhone);
        Nif=findViewById(R.id.etSetingUserNIF);
        email=findViewById(R.id.etSetingUserEmail);
        click=findViewById(R.id.btnAccountantBtn);
    }
}