package com.example.retrofitpractic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    List<PostModel> postList;

    public MyAdapter(Context mContext, List<PostModel> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rows,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.TvUserId.setText(postList.get(i).getUserId());
        myViewHolder.TvTitle.setText(postList.get(i).getTitle());
        myViewHolder.TvBody.setText(postList.get(i).getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TvUserId,TvTitle,TvBody;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvUserId = itemView.findViewById(R.id.TvUserId);
            TvTitle = itemView.findViewById(R.id.TvTitle);
            TvBody = itemView.findViewById(R.id.TvBody);
        }
    }
}
