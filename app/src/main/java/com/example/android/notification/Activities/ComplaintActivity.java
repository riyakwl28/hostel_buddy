package com.example.android.notification.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.notification.Adapters.ComplaintAdapter;
import com.example.android.notification.DetailComplaint;
import com.example.android.notification.R;
import com.example.android.notification.models.complaint;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ComplaintActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private ListView mComplaintsList;
    private ListView listView;
     ArrayList<complaint> complaints;
     private FloatingActionButton fab;

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mMessagesDatabaseReference;

    private android.support.v7.widget.Toolbar toolbar;

    String user_name,user_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaintactivity);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
         listView = (ListView) findViewById(R.id.list);
         mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
         fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);


         user_name=getIntent().getStringExtra("user_name");
         user_image=getIntent().getStringExtra("user_image");
         Log.e("Complaintactivity",user_name);


        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        mFirebaseDatabase = FirebaseDatabase.getInstance();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                complaint currentComplaint = complaints.get(i);
                Intent intent = new Intent(ComplaintActivity.this, DetailComplaint.class);
                intent.putExtra("Detail", currentComplaint);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("announcements");
        mMessagesDatabaseReference.keepSynced(true);

        mMessagesDatabaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        complaints = new ArrayList<complaint>();
                        for (DataSnapshot snap: dataSnapshot.getChildren()) {

                            complaints.add(snap.getValue(complaint.class));
                            Collections.reverse(complaints);
                            ComplaintAdapter adapter = new ComplaintAdapter(ComplaintActivity.this,complaints);

                            listView.setStackFromBottom(false);
                            listView.setAdapter(adapter);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public void fab_click(View v)
    {
        Intent i =new Intent(ComplaintActivity.this, NewComplaint.class);
        i.putExtra("userName",user_name);
        i.putExtra("userImage",user_image);
        startActivity(i);

    }
}
