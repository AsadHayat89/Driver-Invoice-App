package com.example.newtravel.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newtravel.R;

public class AddSecurity extends AppCompatActivity {
    private Button define;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_security);

        define=findViewById(R.id.btnAddNewPIN);
        define.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AddSecurity.this,SecurityPin.class);
                i.putExtra("Type","New");
                startActivity(i);


            }
        });
    }
}