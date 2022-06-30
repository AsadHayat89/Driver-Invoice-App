package com.example.newtravel.GraphAndTables;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtravel.R;
import com.example.newtravel.Static.StaticHolder;
import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Table extends AppCompatActivity {

    private TableLayout tbl;
    private TextView head,DAte;
    String day,month,year,type;
    int checked=0;
    ArrayList<Double> hours=new ArrayList<>();
    ArrayList<Integer> InvNumer=new ArrayList<>();
    private File[] files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        intwidget();

        File directory = new File(String.valueOf(this.getFilesDir().getPath())+"/InvoiceData");
        files = directory.listFiles();
        for(int i=0;i<files.length;i++){
            Log.d("dataFiles",files[i].getName());
        }

        //For Heading Row
        //Log.d("Table",String.valueOf(i));
        TableRow row2 = new TableRow(this);
        row2.setGravity(Gravity.CENTER_HORIZONTAL);
        row2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView txtCol11 = new TextView(this);
        txtCol11.setText("Day");
        txtCol11.setTypeface(null, Typeface.BOLD_ITALIC);
        txtCol11.setBackgroundResource(R.drawable.border_1dp);
        txtCol11.setGravity(Gravity.CENTER_HORIZONTAL);
        row2.addView(txtCol11);

        TextView txtCol31 = new TextView(this);
        txtCol31.setText(String.valueOf("Docs"));
        txtCol31.setTypeface(null, Typeface.BOLD_ITALIC);
        txtCol31.setBackgroundResource(R.drawable.border_1dp);
        txtCol31.setGravity(Gravity.CENTER_HORIZONTAL);
        row2.addView(txtCol31);

        TextView txtCol21 = new TextView(this);
        txtCol21.setText("Values");
        txtCol21.setTypeface(null, Typeface.BOLD_ITALIC);
        txtCol21.setGravity(Gravity.CENTER_HORIZONTAL);
        txtCol21.setBackgroundResource(R.drawable.border_1dp);
        row2.addView(txtCol21);

        tbl.addView(row2);

        //add a new line to the TableLayout:
        final View vline = new View(this);

        vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
        //vline.setBackgroundColor(Color.BLUE);
        tbl.addView(vline);


        type=getIntent().getExtras().getString("Type");
        if(type.equals("Date")){
            head.setText("Sales Table");
            day=getIntent().getExtras().getString("day");
            month=getIntent().getExtras().getString("month");
            year=getIntent().getExtras().getString("year");
            DAte.setText(day+" "+month+" "+year);
            month=getMonth(month);
            if(files.length!=0){
                checked=1;

                for(int i=0;i<24;i++){
                    hours.add(i,0.0);
                    InvNumer.add(i,0);
                }
                try {

                    for (int i = 0; i <files.length; i++) {
                        StringBuilder text = new StringBuilder();
                        String name = files[i].getName();
                        ArrayList<String> data = new ArrayList<>();

                        File currentData=new File(directory,name);
                        FileInputStream instream = new FileInputStream(currentData);
                        if (instream != null) {
                            InputStreamReader inputreader = new InputStreamReader(instream);
                            BufferedReader buffreader = new BufferedReader(inputreader);
                            String line, line1 = "";

                            try {
                                while ((line = buffreader.readLine()) != null) {
                                    String[] a = line.split(":");
                                    data.add(String.valueOf(a[0].trim()));

                                }
                                if (day.equals(data.get(5)) && month.equals(data.get(6)) && year.equals(data.get(7))) {
                                   String[] ho=data.get(8).split("/");

                                   for(int ik=0;ik<24;ik++){

                                        if(ho[0].equals(String.valueOf(ik))){
                                            Double result=hours.get(ik)+Double.parseDouble(data.get(9));
                                            hours.remove(ik);
                                            hours.add(ik,result);
                                            int res=InvNumer.get(ik)+1;
                                            InvNumer.remove(ik);
                                            InvNumer.add(ik,res);

                                        }

                                    }
                                    //Toast.makeText(Graphs.this, "here", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(Table.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }


                } catch (Exception e) {
                    Toast.makeText(Table.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }


        }
        else if(type.equals("Month")){
            head.setText("Sales Month");
            month=getIntent().getExtras().getString("month");
            year=getIntent().getExtras().getString("year");
            DAte.setText(month+" "+year);
            month=getMonth(month);
            if(files.length!=0){
                checked=1;
                //ArrayList<Double> hours=new ArrayList<>();
                for(int i=0;i<31;i++){
                    hours.add(i,0.0);
                    InvNumer.add(i,0);
                }
                try {

                    for (int i = 0; i <files.length; i++) {
                        StringBuilder text = new StringBuilder();
                        String name =files[i].getName();
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
                                    //Log.d("here",month);
                                    for(int ik=0;ik<31;ik++){

                                        if(data.get(5).equals(String.valueOf(ik+1))){
                                            Double result=hours.get(ik)+Double.parseDouble(data.get(9));
                                            Log.d("here",String.valueOf(ik));
                                            hours.remove(ik);
                                            hours.add(ik,result);
                                            int res=InvNumer.get(ik)+1;
                                            InvNumer.remove(ik);
                                            InvNumer.add(ik,res);

                                            //Log.d("AfterREsult",String.valueOf(hours.get(ik)));
                                        }

                                    }
                                    //Toast.makeText(Graphs.this, "here", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(Table.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                } catch (Exception e) {
                    Toast.makeText(Table.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

        }
        else if(type.equals("Year")){
            head.setText("Sales Graph");
            year=getIntent().getExtras().getString("year");
            DAte.setText(year);
            if(files.length!=0){
                checked=1;
                //ArrayList<Double> hours=new ArrayList<>();
                for(int i=0;i<12;i++){
                    hours.add(i,0.0);
                    InvNumer.add(i,0);
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
                                            int res=InvNumer.get(ik)+1;
                                            InvNumer.remove(ik);
                                            InvNumer.add(ik,res);


                                            //Log.d("AfterREsult",String.valueOf(hours.get(ik)));
                                        }

                                    }
                                    //Toast.makeText(Graphs.this, "here", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(Table.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }


                } catch (Exception e) {
                    Toast.makeText(Table.this, "Error1 " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        }


//        Toast.makeText(Table.this, InvNumer.size(), Toast.LENGTH_SHORT).show();
        if(checked==1){
            for(int i=0;i<hours.size();i++){
                //add a new row to the TableLayout
                Log.d("Table",String.valueOf(i));
                TableRow row = new TableRow(this);
                row.setGravity(Gravity.CENTER_HORIZONTAL);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView txtCol1 = new TextView(this);
                if(type.equals("Date")){
                    txtCol1.setText(String.valueOf(i)+":00");
                }
                else if(type.equals("Month")){
                    txtCol1.setText(String.valueOf(i+1));
                    txtCol11.setText("Day");
                }
                else if(type.equals("Year")){
                    txtCol1.setText(getMonthByDate(String.valueOf(i+1)));
                    txtCol11.setText("Month");
                }
                txtCol1.setBackgroundResource(R.drawable.border_1dp);
                txtCol1.setGravity(Gravity.CENTER_HORIZONTAL);
                row.addView(txtCol1);

                TextView txtCol3 = new TextView(this);
                txtCol3.setText(String.valueOf(InvNumer.get(i)));
                txtCol3.setBackgroundResource(R.drawable.border_1dp);
                txtCol3.setGravity(Gravity.CENTER_HORIZONTAL);
                row.addView(txtCol3);

                TextView txtCol2 = new TextView(this);
                txtCol2.setText(String.valueOf(hours.get(i)));
                txtCol2.setGravity(Gravity.CENTER_HORIZONTAL);
                txtCol2.setBackgroundResource(R.drawable.border_1dp);
                row.addView(txtCol2);

                tbl.addView(row);

                //add a new line to the TableLayout:
                final View vline2 = new View(this);

                vline2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                //vline.setBackgroundColor(Color.BLUE);
                tbl.addView(vline2);
            }
        }

    }

    private void intwidget() {
        tbl=findViewById(R.id.tbl_statistics);
        head=findViewById(R.id.TableTexthead);
        DAte=findViewById(R.id.TableTxtDate);
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