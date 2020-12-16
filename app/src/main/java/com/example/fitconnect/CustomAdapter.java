package com.example.fitconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Event> implements View.OnClickListener{

    private ArrayList<Event> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewDate;
        TextView textViewLocation;
        TextView textViewMaxAttendees;
    }

    public CustomAdapter(ArrayList<Event> data, Context context) {
        super(context, R.layout.event_list_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Event dataModel=(Event)object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.event_list_item, parent, false);
            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textView_eli_title);
            viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textView_eli_date);
            viewHolder.textViewLocation = (TextView) convertView.findViewById(R.id.textView_eli_location);
            viewHolder.textViewMaxAttendees = (TextView) convertView.findViewById(R.id.textView_eli_numAttend);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.textViewTitle.setText(dataModel.getTitle());
        viewHolder.textViewDate.setText(dataModel.getDate());
        viewHolder.textViewLocation.setText(dataModel.getLocation());
        viewHolder.textViewMaxAttendees.setText(dataModel.getMaxAttendees());
//        viewHolder.info.setOnClickListener(this);
//        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}