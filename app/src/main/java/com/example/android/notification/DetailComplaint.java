package com.example.android.notification;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.notification.models.complaint;

public class DetailComplaint extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_complaint);
        TextView usernameView = (TextView) findViewById(R.id.detail_username);
        TextView titleView = (TextView) findViewById(R.id.detail_title);
        TextView typeView = (TextView) findViewById(R.id.detail_type);
        TextView descriptionView = (TextView) findViewById(R.id.detail_description);
        ImageView complaintImageView = (ImageView) findViewById(R.id.detail_complaintPhoto);

        mProgressDialog = new ProgressDialog(this);


        complaint announcement = new complaint();
        announcement =  getIntent().getParcelableExtra("Detail");
        String title = announcement.getTitle();
        titleView.setText(title);
        String username = announcement.getUsername();
        usernameView.setText(username);
        String type = announcement.getType();
        typeView.setText(type);
        String description = announcement.getDescription();
        descriptionView.setText(description);
        String uri_string = announcement.getComplaintPhotoUrl();
        if(uri_string != null){
            mProgressDialog.setMessage("Uploading Image...");
            mProgressDialog.show();
            complaintImageView.setVisibility(View.VISIBLE);


            Uri uri = Uri.parse(uri_string);

            Glide.with(DetailComplaint.this).load(uri).into(complaintImageView);
        }
       // Uri complaint_uri = Uri.parse(announcement.getComplaintPhotoUrl());
       // Glide.with(DetailComplaint.this).load(uri).centerCrop().into(complaintImageView);
    }
}
