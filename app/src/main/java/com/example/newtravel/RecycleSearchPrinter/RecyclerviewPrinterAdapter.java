package com.example.newtravel.RecycleSearchPrinter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newtravel.R;
import com.example.newtravel.Showinvoice;
import com.example.newtravel.Static.StaticHolder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public class RecyclerviewPrinterAdapter extends RecyclerView.Adapter<RecyclerviewPrinterAdapter.MyViewHolder> {
    private WeakReference<Context> mContext;
    private List<PrinterItems> itemsList;
    Context context;

//    private ClickListener clickListener;

    RecyclerviewPrinterAdapter(List<PrinterItems> mItemList){
        this.itemsList = mItemList;

    }

    @Override
    public RecyclerviewPrinterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.printitems,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewPrinterAdapter.MyViewHolder holder, final int position) {
        PrinterItems item = itemsList.get(position);
        holder.Name.setText(item.getName());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StaticHolder.mmDevice= item.getDevice();
                boolean retur=item.getDevice().createBond();

                Intent i=new Intent(v.getContext(),PrinterResult.class);
               i.putExtra("Print",item.getDevice());
              v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView Name;
        private LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tvName123);
            itemLayout =  itemView.findViewById(R.id.printeritemLayout);
        }
    }


}