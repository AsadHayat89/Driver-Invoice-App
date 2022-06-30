package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InvoiceMenu extends AppCompatActivity {
    private Button newi,serach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_menu);
        newi=findViewById(R.id.btnInNew);
        serach=findViewById(R.id.btnInSearch);

        newi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InvoiceMenu.this,InvoiveNewMenu.class);
                startActivity(i);
            }
        });
        serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InvoiceMenu.this,SearchInvoiceMenu.class);
                startActivity(i);
            }
        });

    }
}