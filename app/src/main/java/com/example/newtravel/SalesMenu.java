package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newtravel.Sales.SalesPerDay;
import com.example.newtravel.Sales.SalesPerMonth;
import com.example.newtravel.Sales.SalesPerYear;

public class SalesMenu extends AppCompatActivity {
    private Button day,Year,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_menu);
        intwidget();
        callListner();
    }

    private void callListner() {
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SalesMenu.this, SalesPerDay.class);
                startActivity(i);
            }
        });
        Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SalesMenu.this, SalesPerYear.class);
                startActivity(i);
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SalesMenu.this, SalesPerMonth.class);
                startActivity(i);
            }
        });
    }

    private void intwidget() {
        day=findViewById(R.id.btnSalDaliy);
        Year=findViewById(R.id.btnSatYear);
        month=findViewById(R.id.btnSalMonth);
    }
}