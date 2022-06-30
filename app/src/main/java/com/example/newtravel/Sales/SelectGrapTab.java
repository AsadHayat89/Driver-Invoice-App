package com.example.newtravel.Sales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.newtravel.GraphAndTables.Graphs;
import com.example.newtravel.GraphAndTables.Table;
import com.example.newtravel.R;

public class SelectGrapTab extends AppCompatActivity {
    private Button graph,Table1;
    private TextView Date,head;
    String day,month,year,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grap_tab);
        intiwdget();

        type=getIntent().getExtras().getString("Type");
        if(type.equals("Date")){
            head.setText("Search By Date");
            day=getIntent().getExtras().getString("day");
            month=getIntent().getExtras().getString("month");
            year=getIntent().getExtras().getString("year");
            Date.setText(day+" "+month+" "+year);
        }else if(type.equals("Month")){
            head.setText("Search By Month");
            month=getIntent().getExtras().getString("month");
            year=getIntent().getExtras().getString("year");
            Date.setText(month+" "+year);
        }
        else if(type.equals("Year")){
            head.setText("Search By Year");
            year=getIntent().getExtras().getString("year");
            Date.setText(year);
        }

        calllistner();
    }

    private void calllistner() {
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Date")){
                    Intent i=new Intent(SelectGrapTab.this, Graphs.class);
                    i.putExtra("Type",type);
                    i.putExtra("day",day);
                    i.putExtra("month",month);
                    i.putExtra("year",year);
                    startActivity(i);
                }
                else if(type.equals("Month")){
                    Intent i=new Intent(SelectGrapTab.this, Graphs.class);
                    i.putExtra("Type",type);
                    i.putExtra("month",month);
                    i.putExtra("year",year);
                    startActivity(i);
                }
                else if(type.equals("Year")){
                    Intent i=new Intent(SelectGrapTab.this, Graphs.class);
                    i.putExtra("Type",type);
                    i.putExtra("year",year);
                    startActivity(i);
                }


            }
        });
        Table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Date")){
                    Intent i=new Intent(SelectGrapTab.this,Table.class );
                    i.putExtra("Type",type);
                    i.putExtra("day",day);
                    i.putExtra("month",month);
                    i.putExtra("year",year);
                    startActivity(i);
                }
                else if(type.equals("Month")){
                    Intent i=new Intent(SelectGrapTab.this, Table.class);
                    i.putExtra("Type",type);
                    i.putExtra("month",month);
                    i.putExtra("year",year);
                    startActivity(i);
                }
                else if(type.equals("Year")){
                    Intent i=new Intent(SelectGrapTab.this,Table.class);
                    i.putExtra("Type",type);
                    i.putExtra("year",year);
                    startActivity(i);
                }
            }
        });
    }

    private void intiwdget() {
        graph=findViewById(R.id.btnSelectGraph);
        Table1=findViewById(R.id.btnSelectTable);
        Date=findViewById(R.id.textviewdate);
        head=findViewById(R.id.textViewHeadin);
    }

}