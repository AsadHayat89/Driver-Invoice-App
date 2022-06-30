package com.example.newtravel.SearchInvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newtravel.R;

public class SearchDateMenu extends AppCompatActivity {
    private Button day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_date_menu);

    intwidget();
calllistner();
    }

    private void calllistner() {
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SearchDateMenu.this,SearchByDate.class);
                startActivity(i);
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SearchDateMenu.this,SearchByMonth.class);
                startActivity(i);
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SearchDateMenu.this,SearchByYear.class);
                startActivity(i);
            }
        });
    }

    private void intwidget() {
        day=findViewById(R.id.btnSeDaMeDay);
        month=findViewById(R.id.btnSeDaMeMo);
        year=findViewById(R.id.btnSeDaMeYEar);
    }
}