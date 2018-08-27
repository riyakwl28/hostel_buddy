package com.example.android.notification.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.notification.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class SendActivity extends AppCompatActivity {
    private TextView user_id_view;
    private String mUserId;
    private EditText mMessageView;
    private Button mSendBtn;
    private String mCurrentId;
    private String mUserName;
    private ProgressBar mMessageProgress;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        user_id_view=(TextView)findViewById(R.id.user_name_view);
        mMessageView=(EditText)findViewById(R.id.message_view);
        mSendBtn=(Button)findViewById(R.id.send_btn);
        mMessageProgress=(ProgressBar)findViewById(R.id.messageProgress);

        mFirestore=FirebaseFirestore.getInstance();
        mCurrentId=FirebaseAuth.getInstance().getCurrentUser().getUid();

        mUserId=getIntent().getStringExtra("user_id");

        mUserName=getIntent().getStringExtra("user_name");

        user_id_view.setText("Send To " + mUserName);



        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=mMessageView.getText().toString();




                if(!TextUtils.isEmpty(message)){
                    mMessageProgress.setVisibility(View.VISIBLE);

                    Map<String ,Object> notificationMessage=new HashMap<>();
                    notificationMessage.put("message",message);
                    notificationMessage.put("from",mCurrentId);


                    mFirestore.collection("Users/" + mUserId + "/Notifications" ).add(notificationMessage).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(SendActivity.this,"Notification Sent.",Toast.LENGTH_SHORT).show();
                            mMessageView.setText("");
                            mMessageProgress.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SendActivity.this,"Error :"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            mMessageProgress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });
    }
}
