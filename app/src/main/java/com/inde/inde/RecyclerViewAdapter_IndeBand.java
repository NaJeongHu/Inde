package com.inde.inde;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter_IndeBand extends RecyclerView.Adapter<RecyclerViewAdapter_IndeBand.MyViewHolder> {

    private ArrayList<IndeBand> mList;
    private LayoutInflater mInflate;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_groupname;
        private ImageView iv_groupimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_groupname = itemView.findViewById(R.id.tv_groupname);
            iv_groupimage = itemView.findViewById(R.id.iv_groupimage);
        }
    }

    public RecyclerViewAdapter_IndeBand(Context context, ArrayList<IndeBand> items) {
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflate.inflate(R.layout.item_indeband, parent, false);
        MyViewHolder vh = new MyViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!mList.isEmpty()) {
            IndeBand indeband = new IndeBand();
            indeband = mList.get(position);
            if (indeband != null) {
                holder.tv_groupname.setText(indeband.getGroupName());
                Glide.with(context).load(indeband.getGroupImageUrl()).into(holder.iv_groupimage);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
