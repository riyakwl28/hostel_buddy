package com.example.android.notification.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.notification.R;
import com.example.android.notification.models.complaint;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewComplaint extends AppCompatActivity {

    private Button mSubmitButton;
    private EditText mTitleView;
    private EditText mDescriptionView;
    private EditText mTypeView;
    private Spinner mSpinner;
    private String type;
    private TextView name;

    private String mUsername;
    public static final String ANONYMOUS = "anonymous";

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mMessagesDatabaseReference;

    private complaint announcement = new complaint(null,null,null,null,null,null);
    CircleImageView imageView;
    public StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;

    private ProgressDialog mProgressDialog;

    String userName,userImage;

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);

        mProgressDialog = new ProgressDialog(this);

        userName=getIntent().getStringExtra("userName");
        userImage=getIntent().getStringExtra("userImage");

        Log.e("username is",userName +" "+userImage);

        imageView=(CircleImageView)findViewById(R.id.profile_image);
        name=(TextView)findViewById(R.id.name_here);

        name.setText(userName);
        Glide.with(NewComplaint.this).load(userImage).into(imageView);

        mUsername = ANONYMOUS;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();

        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("announcements");
        mTitleView = (EditText)findViewById(R.id.complaint_title);
        mDescriptionView = (EditText)findViewById(R.id.complaint_description);
        mSpinner = (Spinner) findViewById(R.id.spinner_type);

        mTitleView.getBackground().setAlpha(50);
        mDescriptionView.getBackground().setAlpha(50);



        /**mSubmitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //complaint announcement = new complaint(mTitleView.getText().toString(),mTypeView.getText().toString(),mDescriptionView.getText().toString(),mUsername,null,null);
                announcement.setType(mTypeView.getText().toString());
                announcement.setTitle(mTitleView.getText().toString());
                announcement.setDescription(mDescriptionView.getText().toString());
                announcement.setUsername(mUsername);
                mMessagesDatabaseReference.push().setValue(announcement);
                finish();
            }
        });**/

        setUpSpinner();
    }

    public void setUpSpinner(){
        ArrayAdapter typeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_announcement_options, android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mSpinner.setAdapter(typeSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selection = mSpinner.getSelectedItem().toString();
                if (selection.equals(getString(R.string.type_complaint))){
                    type = "Complaint";

                } else if (selection.equals(getString(R.string.type_general_announcement))){
                    type = "General Announcement";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type = "General Announcement";

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();
            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(NewComplaint.this, "Upload done!",Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    announcement.setComplaintPhotoUrl(downloadUri.toString());

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_complaint, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_attach:
                // Save pet to database
                attachComplaintImage();


                return true;
            case R.id.action_send:
                sendComplaint();
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendComplaint(){
        announcement.setType(type);
        announcement.setTitle(mTitleView.getText().toString());
        announcement.setDescription(mDescriptionView.getText().toString());
        announcement.setUsername(userName);
        announcement.setComplaintPhotoUrl(userImage);
        mMessagesDatabaseReference.push().setValue(announcement);


        Glide.with(NewComplaint.this).load(userImage).into(imageView);
    }


    public void attachComplaintImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/jpeg");
        startActivityForResult(intent,GALLERY_INTENT);

    }
}
