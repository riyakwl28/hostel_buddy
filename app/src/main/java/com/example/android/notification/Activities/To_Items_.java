package com.example.android.notification.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.notification.Fragments.UsersFragment;
import com.example.android.notification.R;
import com.example.android.notification.models.To_Items;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class To_Items_ extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    Button b;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String mUserId,mUserName;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to__items_);

        ed1=(EditText)findViewById(R.id.given_name);
        ed2=(EditText) findViewById(R.id.given_to);
        b=(Button)findViewById(R.id.send_here_toItem);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("Users");

        Intent i=getIntent();
        if(i.hasExtra("user_name"))
        {
            username=getIntent().getStringExtra("user_name");
            Log.e("thisactivity",username);
        }
        else
        {
            Log.e("hisactivity","username not passed yet");
        }


//        ed2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(To_Items_.this,UsersListActivity.class);
//                startActivity(i);
//
//
//            }
//        });
        mUserId=getIntent().getStringExtra("user_id1");

        mUserName=getIntent().getStringExtra("user_name1");
        ed2.setText(mUserName);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(ed1.getText()) && !TextUtils.isEmpty(ed2.getText()))
                {
                    To_Items given_item = new To_Items(ed1.getText().toString(),ed2.getText().toString());

                    mFirebaseDatabase.child("test_name").push().setValue(given_item);
                    mFirebaseDatabase.child(username).push().setValue(given_item);
                    Toast.makeText(To_Items_.this, "Successful!", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else
                {
                    Toast.makeText(To_Items_.this, "Please enter both values!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
