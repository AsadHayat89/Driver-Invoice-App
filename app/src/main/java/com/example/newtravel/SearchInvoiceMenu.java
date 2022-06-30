package com.example.newtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newtravel.Recyle.listDataFfromInvoice;
import com.example.newtravel.SearchInvoice.SearchByName;
import com.example.newtravel.SearchInvoice.SearchDateMenu;
import com.example.newtravel.Static.StaticHolder;

public class SearchInvoiceMenu extends AppCompatActivity {
    private Button LaIn,LiAll,BName,Bdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice_menu);
        intwidget();
        calllistner();
    }

    private void calllistner() {
        LaIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String data="Invoice"+ StaticHolder.lastData+".txt";

                SharedPreferences prefs = getSharedPreferences("Project", MODE_PRIVATE);
                String data = prefs.getString("LAstInvoice", "No name defined");//"No name defined" is the default value.
                //int idName = prefs.getInt("idName", 0); //0 is the default value.

                Intent i=new Intent(SearchInvoiceMenu.this,Showinvoice.class);
                i.putExtra("Store","Print");
                i.putExtra("Filename",data);
                startActivity(i);
            }
        });
        LiAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(SearchInvoiceMenu.this, listDataFfromInvoice.class);
                i.putExtra("type","list");
                startActivity(i);
            }
        });
        BName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SearchInvoiceMenu.this, SearchByName.class);
                startActivity(i);
            }
        });
        Bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SearchInvoiceMenu.this, SearchDateMenu.class);
                startActivity(i);
            }
        });
    }


    private void intwidget() {
        LaIn=findViewById(R.id.btnSMLI);
        LiAll=findViewById(R.id.btnSMLA);
        BName=findViewById(R.id.btnSMBN);
        Bdate=findViewById(R.id.btnSMD);
    }

}