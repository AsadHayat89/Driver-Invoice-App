package com.example.newtravel.GraphAndTables;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.R;
import com.example.newtravel.Recyle.Items;
import com.example.newtravel.Static.StaticHolder;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Graphs extends AppCompatActivity {
    private ArrayList<Items> itemsList=new ArrayList<>();
    int checked=0;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    private Button Email;
    private TextView head,date;
    String day,month,year,type;
    private File[] files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        barChart = findViewById(R.id.barChart);
        head=findViewById(R.id.GraphTexthead);
        date=findViewById(R.id.GraphTxtDate);
        Email=findViewById(R.id.btnTableEmail);


        File directory = new File(String.valueOf(this.getFilesDir().getPath())+"/InvoiceData");
        files = directory.listFiles();
        for(int i=0;i<files.length;i++){
            Log.d("dataFiles",files[i].getName());
        }

        type=getIntent().getExtras().getString("Type");
        if(type.equals("Date")){
            head.setText("Sales Graph");
            day=getIntent().getExtras().getString("day");
            month=getIntent().getExtras().getString("month");
            year=getIntent().getExtras().getString("year");
            date.setText(day+" "+month+" "+year);
            month=getMonth(month);
            if(files.length!=0){
                checked=1;
                ArrayList<Double> hours=new ArrayList<>();
                for(int i=0;i<24;i++){
                    hours.add(i,0.0);
                }
                try {

                    for (int i = 0; i <files.length; i++) {
                        StringBuilder text = new StringBuilder();
                        String name = files[i].getName();
                        //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                        ArrayList<String> data = new ArrayList<>();

                        File currentData=new File(directory,name);
                        FileInputStream instream = new FileInputStream(currentData);
                        if (instream != null) {
                            InputStreamReader inputreader = new InputStreamReader(instream);
                            BufferedReader buffreader = new BufferedReader(inputreader);
                            String line, line1 = "";

                            try {
                                while ((line = buffreader.readLine()) != null) {
                                    //line1+=line;
                                    String[] a = line.split(":");
                                    data.add(String.valueOf(a[0].trim()));
                                    // Log.d("dataIncide",a[0]);

                                }
                                //Toast.makeText(Graphs.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
                                if (day.equals(data.get(5)) && month.equals(data.get(6)) && year.equals(data.get(7))) {
                                    //   itemsList.add(new Items(data.get(5) + "/" + data.get(6) + "/" + data.get(7), data.get(8), data.get(10), data.get(9), "Invoice" + data.get(13) + ".txt"));
                                    String[] ho=data.get(8).split("/");

                                    // Log.d("checkAgin",String.valueOf(ho[0]));
                                    for(int ik=0;ik<24;ik++){

                                        if(ho[0].equals(String.valueOf(ik))){
                                            Double result=hours.get(ik)+Double.parseDouble(data.get(9));
//                                        Log.d("here",String.valueOf(ik));
                                            hours.remove(ik);

                                            hours.add(ik,result);

                                        }

                                    }
                                    //Toast.makeText(Graphs.this, "here", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(Graphs.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                    barEntries = new ArrayList<>();

                    for(int l=0;l<24;l++){
                        // Log.d("PerDAte",String.valueOf(hours.get(l)));
                        if(hours.get(l)==0.0){
                            barEntries.add(new BarEntry(l,0f));
                        }
                        else{
                            barEntries.add(new BarEntry(l,Float.valueOf(String.valueOf(hours.get(l)))));
                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(Graphs.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }


        }else if(type.equals("Month")){
            head.setText("Sales Graph");
            month=getIntent().getExtras().getString("month");
            year=getIntent().getExtras().getString("year");
            date.setText(month+" "+year);
            month=getMonth(month);
            if(files.length!=0){
                checked=1;
                ArrayList<Double> hours=new ArrayList<>();
                for(int i=0;i<32;i++){
                    hours.add(i,0.0);
                }
                try {

                    for (int i = 0; i <files.length; i++) {
                        StringBuilder text = new StringBuilder();
                        String name = files[i].getName();
                        //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                        ArrayList<String> data = new ArrayList<>();

                        File currentData=new File(directory,name);
                        FileInputStream instream = new FileInputStream(currentData);
                        if (instream != null) {
                            InputStreamReader inputreader = new InputStreamReader(instream);
                            BufferedReader buffreader = new BufferedReader(inputreader);
                            String line, line1 = "";

                            try {
                                while ((line = buffreader.readLine()) != null) {
                                    //line1+=line;
                                    String[] a = line.split(":");
                                    data.add(String.valueOf(a[0].trim()));
                                    // Log.d("dataIncide",a[0]);

                                }

                                //Toast.makeText(Graphs.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
                                if (month.equals(data.get(6)) && year.equals(data.get(7))) {
                                    for(int ik=0;ik<31;ik++){

                                        if(data.get(5).equals(String.valueOf(ik+1))){
                                            Log.d("PreREsult",String.valueOf((ik)));
                                            Double result=hours.get(ik)+Double.parseDouble(data.get(9));
    //                                        Log.d("here",String.valueOf(ik));
                                            hours.remove(ik);
                                            hours.add(ik,result);

                                            //Log.d("AfterREsult",String.valueOf(hours.get(ik)));
                                        }

                                    }
                                    //Toast.makeText(Graphs.this, "here", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(Graphs.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                    //Adding data into Main Arraylist
                    barEntries = new ArrayList<>();

                    for(int l=0;l<31;l++){
                        Log.d("Results",String.valueOf(hours.get(l)));
                        if(hours.get(l)==0.0){
                            barEntries.add(new BarEntry(l+1,0f));
                        }
                        else{
                            barEntries.add(new BarEntry(l+1,Float.valueOf(String.valueOf(hours.get(l)))));
                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(Graphs.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

        }
        else if(type.equals("Year")){
            head.setText("Sales Graph");
            year=getIntent().getExtras().getString("year");
            date.setText(year);
            if(files.length!=0){
                checked=1;
                ArrayList<Double> hours=new ArrayList<>();
                for(int i=0;i<12;i++){
                    hours.add(i,0.0);
                }
                try {

                    for (int i = 0; i <files.length; i++) {
                        StringBuilder text = new StringBuilder();
                        String name = files[i].getName();
                        //Toast.makeText(listDataFfromInvoice.this, name, Toast.LENGTH_SHORT).show();
                        ArrayList<String> data = new ArrayList<>();

                        File currentData=new File(directory,name);
                        FileInputStream instream = new FileInputStream(currentData);
                        if (instream != null) {
                            InputStreamReader inputreader = new InputStreamReader(instream);
                            BufferedReader buffreader = new BufferedReader(inputreader);
                            String line, line1 = "";

                            try {
                                while ((line = buffreader.readLine()) != null) {
                                    //line1+=line;
                                    String[] a = line.split(":");
                                    data.add(String.valueOf(a[0].trim()));
                                    // Log.d("dataIncide",a[0]);

                                }

                                //Toast.makeText(Graphs.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
                                if (year.equals(data.get(7))) {
                                    for(int ik=0;ik<12;ik++){

                                        if(data.get(6).equals(String.valueOf(ik+1))){

                                            Double result=hours.get(ik)+Double.parseDouble(data.get(9));
                                            hours.remove(ik);
                                            hours.add(ik,result);

                                            //Log.d("AfterREsult",String.valueOf(hours.get(ik)));
                                        }

                                    }
                                    //Toast.makeText(Graphs.this, "here", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(Graphs.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                    //Adding data into Main Arraylist
                    barEntries = new ArrayList<>();

                    for(int l=0;l<12;l++){
                        if(hours.get(l)==0.0){
                            barEntries.add(new BarEntry(l+1,0f));
                        }
                        else{

                            barEntries.add(new BarEntry(l+1,Float.valueOf(String.valueOf(hours.get(l)))));
                        }


                    }

                } catch (Exception e) {
                    Toast.makeText(Graphs.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        }

//        getEntries();

        if(checked==1){
            barDataSet = new BarDataSet(barEntries,"Data Set");
            barData = new BarData(barDataSet);

            barChart.setData(barData);
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);
        }


    }
    private void getEntries(){

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f,2));
        barEntries.add(new BarEntry(2f,4));
        barEntries.add(new BarEntry(3f,1));
        barEntries.add(new BarEntry(5f,5));
        barEntries.add(new BarEntry(6f,3));
        barEntries.add(new BarEntry(7f,2));


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
    private String getMonthByDate(String mon){
        String month="";
        if(mon.equals("1")){
            month="Jan";

        }
        else if(mon.equals("2")){
            month="Feb";

        }
        else if(mon.equals("3")){
            month="Mar";

        }
        else if(mon.equals("4")){
            month="APP";

        }
        else if(mon.equals("5")){
            month="May";

        }
        else if(mon.equals("6")){
            month="Jun";

        }
        else if(mon.equals("7")){
            month="Jul";

        }
        else if(mon.equals("8")){
            month="Agu";

        }
        else if(mon.equals("9")){
            month="Spe";

        }
        else if(mon.equals("10")){
            month="Oct";

        }
        else if(mon.equals("11")){
            month="Nov";

        }
        else if(mon.equals("12")){
            month="Dec";

        }
        return month;
    }
}