package com.example.newtravel.Sales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.newtravel.R;
import com.example.newtravel.Recyle.listDataFfromInvoice;
import com.example.newtravel.SearchInvoice.SearchByYear;

import java.util.ArrayList;

public class SalesPerYear extends AppCompatActivity {
    private Button click;
    private Spinner year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_per_year);
        intwidget();
        calllistenr();
    }
    private void calllistenr() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(SearchByDate.this,"here",Toast.LENGTH_SHORT).show();

                String Year=year.getSelectedItem().toString();

                Intent i=new Intent(SalesPerYear.this, SelectGrapTab.class);
                i.putExtra("Type","Year");
                i.putExtra("year",Year);
                startActivity(i);

            }
        });
    }

    private void intwidget() {
        year=findViewById(R.id.etSalesYeYear);
        click=findViewById(R.id.btnSalesByyearSearcj);


        //Data fro years
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=1992;i<=2050;i++){
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(arrayAdapter);

    }
}