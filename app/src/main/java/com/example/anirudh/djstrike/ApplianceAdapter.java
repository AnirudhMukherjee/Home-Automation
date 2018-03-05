package com.example.anirudh.djstrike;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by aniru on 16-02-2018.
 */

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.MyViewHolder> {

//    private final View.OnClickListener mOnClickListener = new On;
    private Context mContext;
    private List<Appliance> applianceList;
    private RecyclerViewClickListener mListener;

    public ApplianceAdapter(Context mContext, List<Appliance> applianceList, RecyclerViewClickListener listener) {
        mListener = listener;
        this.mContext = mContext;
        this.applianceList = applianceList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title1,count;
        public ImageView thumbnail;
        public RecyclerViewClickListener mListener;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            title1 = itemView.findViewById(R.id.title);
            count = itemView.findViewById(R.id.count);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            mListener = listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view,getAdapterPosition());

        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.appliance_card,parent,false);
//        view.setOnClickListener(mOnClickListener);
        return new MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Appliance appliance = applianceList.get(position);
        holder.title1.setText(appliance.getName());
        holder.count.setText(appliance.getNumber());
        Glide.with(mContext).load(appliance.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return applianceList.size();
    }
}
