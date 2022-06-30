package com.example.newtravel.Printer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newtravel.R;
import com.example.newtravel.RecycleSearchPrinter.listDataFfromPrinter;

public class FoundPrinter extends AppCompatActivity {
    // android built in classes for bluetooth operations
private Button Add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_printer);
        Add=findViewById(R.id.btnAddNewPIN);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FoundPrinter.this, listDataFfromPrinter.class);
                startActivity(i);
            }
        });
    }
}