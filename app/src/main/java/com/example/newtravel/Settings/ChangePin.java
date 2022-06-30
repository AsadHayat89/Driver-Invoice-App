package com.example.newtravel.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.newtravel.R;

public class ChangePin extends AppCompatActivity {

    private Switch enable12;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin2);

        intwidget();
        callListner();
    }

    private void callListner() {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ChangePin.this,SecurityPin.class);
                i.putExtra("Type","Change");
                startActivity(i);

            }
        });
    }

    private void intwidget() {
        change=findViewById(R.id.btnAccountantBtn);
        //enable12=findViewById(R.id.swtChagnePinEnable);
    }
}