package com.project.hackathon.camara.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.hackathon.camara.app.InformationSuspectedActivity;
import com.project.hackathon.camara.app.R;
import com.project.hackathon.camara.app.model.Suspected;

import java.util.List;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class ProductSuspectedCustomAdapter extends RecyclerView.Adapter<ProductSuspectedCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<Suspected> suspectedList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_value_original;
        TextView tv_value_suspected;
        LinearLayout ll_line;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_value_original = (TextView) view.findViewById(R.id.tv_value_original);
            tv_value_suspected = (TextView) view.findViewById(R.id.tv_value_suspected);
            ll_line = (LinearLayout) view.findViewById(R.id.ll_line);
        }
    }

    public ProductSuspectedCustomAdapter(Context mContext, List<Suspected> suspectedList) {
        this.mContext = mContext;
        this.suspectedList = suspectedList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_product_suspected, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Suspected suspected = suspectedList.get(position);
        holder.tv_name.setText("" + suspected.getName());
        holder.tv_value_original.setText("" + suspected.getValue_original());
        holder.tv_value_suspected.setText("" + suspected.getValue_suspected());

    }

    @Override
    public int getItemCount() {
        return suspectedList.size();
    }
}