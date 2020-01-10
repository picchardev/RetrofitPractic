package com.example.retrofitpractic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyAdapterNew<T> extends RecyclerView.Adapter<MyAdapterNew.MyViewHolder> {

    Context mContext;
    ArrayList<T>itemList;

    public MyAdapterNew(Context mContext, ArrayList<T> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterNew.MyViewHolder myViewHolder, int i) {

    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
