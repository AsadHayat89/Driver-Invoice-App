package com.example.newtravel.Sales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.newtravel.R;
import com.example.newtravel.SearchInvoice.SearchByYear;

import java.util.ArrayList;

public class SalesPerDay extends AppCompatActivity {
    private Button click;
    private Spinner year,day,month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_per_day);

        intwidget();
        calllistner();
    }

    private void calllistner() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Day=day.getSelectedItem().toString();
                String Year=year.getSelectedItem().toString();
                String Mon=month.getSelectedItem().toString();

                Intent i=new Intent(SalesPerDay.this, SelectGrapTab.class);
                i.putExtra("Type","Date");
                i.putExtra("day",Day);
                i.putExtra("month",Mon);
                i.putExtra("year",Year);
                startActivity(i);
            }
        });
    }


    private void intwidget() {
        day=findViewById(R.id.etSalesDay);
        month=findViewById(R.id.btnSalesMonMONTH);
        year=findViewById(R.id.btnSalesMonYear2);
        click=findViewById(R.id.btnSalesByMonthButn2);

        //Data fro years
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=1992;i<=2050;i++){
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(arrayAdapter);

        //Daty for dates
        ArrayList<String> arrayListDate = new ArrayList<>();
        for(int i=1;i<=31;i++){
            arrayListDate.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapterDate = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListDate);
        arrayAdapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(arrayAdapterDate);

        //data for months
        ArrayList<String> arrayListMonths = new ArrayList<>();
        arrayListMonths.add("Jan");
        arrayListMonths.add("Feb");
        arrayListMonths.add("Mar");
        arrayListMonths.add("APP");
        arrayListMonths.add("May");
        arrayListMonths.add("Jun");
        arrayListMonths.add("Jul");
        arrayListMonths.add("Agu");
        arrayListMonths.add("Spe");
        arrayListMonths.add("Oct");
        arrayListMonths.add("Nov");
        arrayListMonths.add("Dec");
        ArrayAdapter<String> arrayAdapterMonth = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayListMonths);
        arrayAdapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(arrayAdapterMonth);
    }
}