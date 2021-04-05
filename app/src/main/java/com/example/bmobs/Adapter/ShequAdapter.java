package com.example.bmobs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bmobs.R;
import com.example.bmobs.TongXun.TongxunUtil.YiChat;
import com.example.bmobs.shujulei.ShequItem;

import java.util.List;

public class ShequAdapter extends RecyclerView.Adapter<ShequAdapter.ViewHolder> {
    List<ShequItem> msheList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
         TextView name;
         TextView content;
         CardView cardView;
     public ViewHolder(View view){
         super(view);
         cardView =(CardView) view;
      name=(TextView)view.findViewById(R.id.she_item_name);
      content=(TextView)view.findViewById(R.id.she_item_content);
     }

    }
    public ShequAdapter( List<ShequItem> list) {
        msheList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.w_she_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, YiChat.class);
                mContext.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
ShequItem shequItem=msheList.get(position);
holder.name.setText(shequItem.getName());
holder.content.setText(shequItem.getContent());
    }

    @Override
    public int getItemCount() {
        return msheList.size();
    }
}
