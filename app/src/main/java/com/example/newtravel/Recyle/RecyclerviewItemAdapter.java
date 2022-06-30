package com.example.newtravel.Recyle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newtravel.R;
import com.example.newtravel.Showinvoice;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecyclerviewItemAdapter extends RecyclerView.Adapter<RecyclerviewItemAdapter.MyViewHolder> {
    private WeakReference<Context> mContext;
    private List<Items> itemsList;
    Context context;
//    private ClickListener clickListener;

    RecyclerviewItemAdapter(List<Items> mItemList){
        this.itemsList = mItemList;

    }

    @Override
    public RecyclerviewItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewItemAdapter.MyViewHolder holder, final int position) {
        final Items item = itemsList.get(position);
        holder.Number.setText(item.getName());
        holder.time.setText(String.valueOf(item.getTime()));
        holder.Date.setText(String.valueOf(item.getDate()));
        holder.Total.setText(String.valueOf(item.getTotal()));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
try {
    //Toast.makeText(v.getContext(),"String.valueOf(item.getUId())",Toast.LENGTH_SHORT).show();
    //SharedPreferences sha = (SharedPreferences) mContext.get().getSharedPreferences("Invoice", Context.MODE_PRIVATE);
    //Toast.makeText(v.getContext(),String.valueOf(item.getUId()),Toast.LENGTH_SHORT).show();
    //SharedPreferences.Editor editor = sha.edit();
    //editor.putString("Key", String.valueOf(item.getUId()));
    //editor.apply();

    Intent i = new Intent(v.getContext(), Showinvoice.class);
    i.putExtra("Store","Print");
    i.putExtra("Filename",String.valueOf(item.getUId()));
    v.getContext().startActivity(i);
}catch (Exception e){
    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
}
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView Number,time,Date,Total;
        private LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            Number = itemView.findViewById(R.id.tvNumber);
           time = itemView.findViewById(R.id.tvtime);
           Date=itemView.findViewById(R.id.tvDate);
            Total=itemView.findViewById(R.id.tvTotal);

            itemLayout =  itemView.findViewById(R.id.itemLayout);
        }
    }
}