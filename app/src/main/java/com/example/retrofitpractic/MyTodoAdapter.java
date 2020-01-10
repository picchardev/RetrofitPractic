package com.example.retrofitpractic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyTodoAdapter extends RecyclerView.Adapter<MyTodoAdapter.MyTodoViewHolder> {

    Context mContext;
    List<Todo> getTodoList;

    public MyTodoAdapter(Context mContext, List<Todo> getTodoList) {
        this.mContext = mContext;
        this.getTodoList = getTodoList;
    }

    @NonNull
    @Override
    public MyTodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyTodoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rows,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyTodoViewHolder myTodoViewHolder, int i) {

        myTodoViewHolder.TvId.setText("Id - "+Integer.toString(getTodoList.get(i).getId()));
        myTodoViewHolder.TvUserId.setText("UserId - "+Integer.toString(getTodoList.get(i).getUserId()));
        myTodoViewHolder.TvTitle.setText("Title - "+getTodoList.get(i).getTitle());
        myTodoViewHolder.TvBody.setText("Completed - "+Boolean.toString(getTodoList.get(i).getCompleted()));

    }

    @Override
    public int getItemCount() {
        return getTodoList.size();
    }

    public class MyTodoViewHolder extends RecyclerView.ViewHolder {

        TextView TvId,TvUserId,TvTitle,TvBody;

        public MyTodoViewHolder(@NonNull View itemView) {
            super(itemView);

            TvId = itemView.findViewById(R.id.TvId);
            TvUserId = itemView.findViewById(R.id.TvUserId);
            TvTitle = itemView.findViewById(R.id.TvTitle);
            TvBody = itemView.findViewById(R.id.TvBody);

        }
    }
}
