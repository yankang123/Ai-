package com.example.bmobs.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bmobs.R;
import com.example.bmobs.shujulei.Msg;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
private  List<Msg> mMsgList;
    public ChatAdapter(List<Msg> msgListm) {
        mMsgList=msgListm;
    }
static class ViewHolder extends RecyclerView.ViewHolder{
LinearLayout leftlayout;
LinearLayout rightlayout;
TextView leftmsg;
TextView rightmsg;
public ViewHolder(View view){
    super(view);
    leftlayout=(LinearLayout)view.findViewById(R.id.left_layout);
    rightlayout=(LinearLayout)view.findViewById(R.id.right_layout);
    leftmsg=(TextView)view.findViewById(R.id.left_msg);
    rightmsg=(TextView)view.findViewById(R.id.right_msg);

}
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.w_msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg=mMsgList.get(position);
   if(msg.getType()==msg.Type_Receive){
       holder.leftlayout.setVisibility(View.VISIBLE);
       holder.rightlayout.setVisibility(View.GONE);
       holder.leftmsg.setText(msg.getContent());
   }else if(msg.getType()==msg.Type_Send){
       holder.rightlayout.setVisibility(View.VISIBLE);
       holder.leftlayout.setVisibility(View.GONE);
       holder.rightmsg.setText(msg.getContent());
   }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
