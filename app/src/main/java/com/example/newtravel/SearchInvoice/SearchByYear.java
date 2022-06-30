package com.example.newtravel.SearchInvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.newtravel.R;
import com.example.newtravel.Recyle.listDataFfromInvoice;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SearchByYear extends AppCompatActivity {
    private Button click;
    private Spinner year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_year);
        intwidget();
        calllistenr();
    }

    private void calllistenr() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(SearchByDate.this,"here",Toast.LENGTH_SHORT).show();

                String Year=year.getSelectedItem().toString();

                    Intent i=new Intent(SearchByYear.this, listDataFfromInvoice.class);
                    i.putExtra("type","SearchByYear");
                    i.putExtra("Year",Year);

                    startActivity(i);

            }
        });
    }

    private void intwidget() {
        year=findViewById(R.id.etSeyYeYear);
        click=findViewById(R.id.btnSeByyearSearcj);


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