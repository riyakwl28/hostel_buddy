package com.example.android.notification.Adapters;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.notification.models.complaint;
import com.example.android.notification.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HP on 14-04-2018.
 */

public class ComplaintAdapter extends ArrayAdapter<complaint> {


    public ComplaintAdapter(Context context, ArrayList<complaint> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.items_complaint, parent, false);
        }

        TextView typeView = (TextView) convertView.findViewById(R.id.type_text_view);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);

        TextView usernameTextView = (TextView) convertView.findViewById(R.id.username_text_view);
        CircleImageView imageView=(CircleImageView)convertView.findViewById(R.id.profile_image1);



        complaint announcement = getItem(position);

        typeView.setText(announcement.getType());
        titleTextView.setText(announcement.getTitle());
        usernameTextView.setText(announcement.getUsername());
        Log.e("imageurl",announcement.getComplaintPhotoUrl());


        String x=announcement.getComplaintPhotoUrl();
        Glide.with(getContext()).load(x).into(imageView);

        return convertView;

    }
}
