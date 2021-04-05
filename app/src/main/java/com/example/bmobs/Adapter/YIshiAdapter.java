package com.example.bmobs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bmobs.Gn.ShowwzActivity;
import com.example.bmobs.R;
import com.example.bmobs.shujulei.Wenzhangad;

import java.util.List;

public class YIshiAdapter extends RecyclerView.Adapter<YIshiAdapter.ViewHolder> {
    private Context mcontext;
    private List<Wenzhangad> mwenzhanglist;

    public YIshiAdapter(List<Wenzhangad> wenzhanglist) {
        mwenzhanglist = wenzhanglist;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view) {
        super(view);
        cardView = (CardView) view;
        imageView = (ImageView) view.findViewById(R.id.wenzhang_image);
        textView = (TextView) view.findViewById(R.id.wenzhang_name);
    }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(mcontext).inflate(R.layout.wenzhang_item, parent, false);
        final ViewHolder holder =new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Wenzhangad wzz=mwenzhanglist.get(position);
                Intent intent=new Intent(mcontext, ShowwzActivity.class);
                intent.putExtra("wzname",wzz.getTitle());
                mcontext.startActivity(intent);
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wenzhangad wz = mwenzhanglist.get(position);
        holder.textView.setText(wz.getTitle());
        if(wz.getImageId()!=0)
        Glide.with(mcontext).load(wz.getImageId()).into(holder.imageView);
        else
            Glide.with(mcontext).load(wz.getUrl()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return mwenzhanglist.size();
    }
}