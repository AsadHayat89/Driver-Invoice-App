package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InvoiveNewMenu extends AppCompatActivity {
    private Button simpl,Recp,Man;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoive_new_menu);

        simpl=findViewById(R.id.btnInvNewSim);
        Recp=findViewById(R.id.btnInvNewRec);
        Man=findViewById(R.id.btnInvNewMan);

        simpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InvoiveNewMenu.this,SimplifiedInvoice.class);
                startActivity(i);
            }
        });
        Man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InvoiveNewMenu.this,MAnualReciept.class);
                startActivity(i);
            }
        });
        Recp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InvoiveNewMenu.this,MAnualReciept.class);
                startActivity(i);
            }
        });
    }
}