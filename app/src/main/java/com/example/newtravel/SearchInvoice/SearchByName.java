package com.example.newtravel.SearchInvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.newtravel.R;
import com.example.newtravel.Recyle.listDataFfromInvoice;
import com.google.android.material.textfield.TextInputLayout;

public class SearchByName extends AppCompatActivity {
    private Button seach;
    private TextInputLayout namd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);
        intwidget();
        calllistnare();
    }

    private void calllistnare() {
    seach.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name=namd.getEditText().getText().toString();
            if(TextUtils.isEmpty(name)){
                namd.setError("Name Cann't be empty");
            }
            else{
                Intent i=new Intent(SearchByName.this, listDataFfromInvoice.class);
                i.putExtra("type","Byname");
                i.putExtra("SearchName",name);
                startActivity(i);
            }
        }
    });
    }

    private void intwidget() {
    namd=findViewById(R.id.etSetingUserName);
    seach=findViewById(R.id.btnSeByDAtBtn);
    }
}