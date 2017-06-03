package com.project.hackathon.camara.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class SuspectedCustomAdapter extends ArrayAdapter<Suspected> implements View.OnClickListener{

    private ArrayList<Suspected> dataSet;
    private Context mContext;

    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        TextView  tv_type;
        TextView  tv_name;
        TextView  tv_score;
        ImageView iv_icon;
        ImageView iv_icon_score;
        LinearLayout ll_line;
    }

    public SuspectedCustomAdapter(ArrayList<Suspected> data, Context context) {
        super(context, R.layout.row_item_suspected, data);

        this.dataSet = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final Suspected suspected = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_suspected, parent, false);

            viewHolder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_score = (TextView) convertView.findViewById(R.id.tv_score);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.iv_icon_score = (ImageView) convertView.findViewById(R.id.iv_icon_score);
            viewHolder.ll_line = (LinearLayout) convertView.findViewById(R.id.ll_line);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tv_name.setText("" + suspected.getName());
        viewHolder.tv_type.setText("" + suspected.getType());
        viewHolder.tv_score.setText("" + suspected.getScore());
        viewHolder.iv_icon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.iv_icon_score.setImageResource(R.mipmap.ic_launcher);

        viewHolder.ll_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformationSuspectedActivity.class);
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }
}
