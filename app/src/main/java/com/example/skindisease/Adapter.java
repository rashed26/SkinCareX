package com.example.skindisease;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder>{

    private List<PostPojo> dataList;
    private Context context;

    public Adapter() {
    }

    public Adapter(Context context, List<PostPojo> dataList) {
        this.context=context;
        this.dataList = dataList;
    }


    public CustomViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
//        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=LayoutInflater.from(context).inflate(R.layout.designrestapi,parent,false);
        return new CustomViewHolder(view);
    }

    public void onBindViewHolder(CustomViewHolder holder,int position)
    {
        holder.titleRest.setText(dataList.get(position).getTitle());
        holder.posts.setText(dataList.get(position).getBody());
    }
    public int getItemCount(){return dataList.size();}

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView titleRest,posts;

        CustomViewHolder(View itemView)
        {
            super(itemView);

            titleRest=itemView.findViewById(R.id.titleRest);
            posts=itemView.findViewById(R.id.body);

        }
    }

}
