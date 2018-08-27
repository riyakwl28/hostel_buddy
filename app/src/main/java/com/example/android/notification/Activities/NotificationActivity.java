package com.example.android.notification.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.notification.R;

public class NotificationActivity extends AppCompatActivity {
    private TextView mNotifData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String dataMessage=getIntent().getStringExtra("message");
        String dataFrom=getIntent().getStringExtra("from_user_id");

        mNotifData=(TextView)findViewById(R.id.notif_text);
        mNotifData.setText("From:" + dataFrom + " | Message" + dataMessage);
    }
}
