package com.example.android.notification.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.android.notification.Adapters.ComplaintAdapter;
import com.example.android.notification.Adapters.ToItemsAdapter;
import com.example.android.notification.R;
import com.example.android.notification.models.To_Items;
import com.example.android.notification.models.complaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Show_All_Items_ extends AppCompatActivity {

    private ListView listView;
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mMessagesDatabaseReference;
    ArrayList<To_Items> items;
    String user_name;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__all__items_);

        if (getIntent().hasExtra("user_name")) {
            Log.e("Show ITems", "got it");
            user_name = getIntent().getStringExtra("user_name");
        } else {
            Log.e("Show ITems", "not received in ");
        }
        listView = (ListView) findViewById(R.id.list_to_items);

        fab = findViewById(R.id.floatingActionButton_to_items);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Show_All_Items_.this,To_Items_.class);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(user_name);
        mMessagesDatabaseReference.keepSynced(true);

        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items = new ArrayList<To_Items>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {

                    items.add(snap.getValue(To_Items.class));
                    Log.e("size",String.valueOf(items.size()));
                    Collections.reverse(items);
                    ToItemsAdapter adapter = new ToItemsAdapter(Show_All_Items_.this, items);
                    listView.setAdapter(adapter);
                    listView.setStackFromBottom(false);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
