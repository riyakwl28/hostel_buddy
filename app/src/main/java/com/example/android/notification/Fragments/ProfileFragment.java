package com.example.android.notification.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.notification.Activities.ComplaintActivity;
import com.example.android.notification.Activities.LoginActivity;
import com.example.android.notification.Activities.NewComplaint;
import com.example.android.notification.Activities.Show_All_Items_;
import com.example.android.notification.Activities.To_Items_;
import com.example.android.notification.PocketMain;
import com.example.android.notification.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Button mLogoutBtn,showitems,toItem,portal,pocketm;
    private FirebaseAuth mAuth;
    String user_name,user_image;



    private TextView mProfileName;
    private FirebaseFirestore mFirestore;
    private ImageView mProfileImage;
    private  String mUserId;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
    mAuth=FirebaseAuth.getInstance();
    mFirestore=FirebaseFirestore.getInstance();
    mUserId=mAuth.getCurrentUser().getUid();


//    showitems=(Button)view.findViewById(R.id.show_items);
//    toItem=(Button)view.findViewById(R.id.To_Items);



    mProfileImage=(ImageView)view.findViewById(R.id.profile_image);
    mProfileName=(TextView)view.findViewById(R.id.profile_name);
    mFirestore.collection("Users").document(mUserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            user_name=documentSnapshot.getString("name");
            user_image=documentSnapshot.getString("image");
            mProfileName.setText(user_name);



            RequestOptions placeHolderOption=new RequestOptions();
            placeHolderOption.placeholder(R.drawable.download);
            Glide.with(container.getContext()).setDefaultRequestOptions(placeHolderOption).load(user_image).into(mProfileImage);


        }
    });


//  showitems.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent i = new Intent(container.getContext(), Show_All_Items_.class);
//            i.putExtra("user_name", user_name);
//            startActivity(i);    }
//    });
//
//    toItem.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent i = new Intent(container.getContext(), To_Items_.class);
//            Log.e("ProfileFragment",user_name);
//            i.putExtra("user_name", user_name);
//            startActivity(i);
//        }
//    });





         return view;

    }



}
