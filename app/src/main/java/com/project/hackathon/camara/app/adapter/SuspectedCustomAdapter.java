package com.project.hackathon.camara.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.hackathon.camara.app.InformationSuspectedActivity;
import com.project.hackathon.camara.app.MainActivity;
import com.project.hackathon.camara.app.R;
import com.project.hackathon.camara.app.model.Suspected;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class SuspectedCustomAdapter extends RecyclerView.Adapter<SuspectedCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<Suspected> suspectedList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type;
        TextView tv_name;
        TextView tv_score;
        ImageView iv_icon;
        ImageView iv_icon_score;
        LinearLayout ll_line;

        public MyViewHolder(View view) {
            super(view);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_score = (TextView) view.findViewById(R.id.tv_score);
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            iv_icon_score = (ImageView) view.findViewById(R.id.iv_icon_score);
            ll_line = (LinearLayout) view.findViewById(R.id.ll_line);
        }
    }

    public SuspectedCustomAdapter(Context mContext, List<Suspected> suspectedList) {
        this.mContext = mContext;
        this.suspectedList = suspectedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_suspected, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Suspected suspected = suspectedList.get(position);
        holder.tv_name.setText("" + suspected.getName());
        holder.tv_type.setText("" + suspected.getType());
        holder.tv_score.setText("" + suspected.getScore());
        holder.iv_icon.setImageResource(R.mipmap.ic_launcher);
        holder.iv_icon_score.setImageResource(R.mipmap.ic_launcher);

        holder.ll_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformationSuspectedActivity.class);
                intent.putExtra("id", "" + suspected.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return suspectedList.size();
    }
}

