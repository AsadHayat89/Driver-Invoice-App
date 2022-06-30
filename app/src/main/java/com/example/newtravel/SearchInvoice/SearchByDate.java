package com.example.newtravel.SearchInvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.newtravel.R;
import com.example.newtravel.Recyle.listDataFfromInvoice;

import java.util.ArrayList;

public class SearchByDate extends AppCompatActivity {

    private Button click;
    private Spinner year,day,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_date);
        intwidget();
        calllistner();
    }

    private void calllistner() {
    click.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(SearchByDate.this,"here",Toast.LENGTH_SHORT).show();
            String Day=day.getSelectedItem().toString();
            String Year=year.getSelectedItem().toString();
            String Mon=getMonth(month.getSelectedItem().toString());


                Intent i=new Intent(SearchByDate.this, listDataFfromInvoice.class);
                i.putExtra("type","SearchByDate");
                i.putExtra("day",Day);
                i.putExtra("Year",Year);
                i.putExtra("Month",Mon);
                startActivity(i);

        }
    });
    }
private String getMonth(String mon){
        String month="";
        if(mon.equals("Jan")){
        month="1";

        }
        else if(mon.equals("Feb")){
            month="2";

        }
        else if(mon.equals("Mar")){
            month="3";

        }
        else if(mon.equals("APP")){
            month="4";

        }
        else if(mon.equals("May")){
            month="5";

        }
        else if(mon.equals("Jun")){
            month="6";

        }
        else if(mon.equals("Jul")){
            month="7";

        }
        else if(mon.equals("Agu")){
            month="8";

        }
        else if(mon.equals("Spe")){
            month="9";

        }
        else if(mon.equals("Oct")){
            month="10";

        }
        else if(mon.equals("Nov")){
            month="11";

        }
        else if(mon.equals("Dec")){
            month="12";

        }
        return month;
}
    private void intwidget() {
        day=findViewById(R.id.etSetingUserName);
        month=findViewById(R.id.btnSeMonMONTH);
        year=findViewById(R.id.btnSeMonYear2);
        click=findViewById(R.id.btnSeByMonthButn2);

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