package com.example.android.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * Created by Riya Khandelwal on 15-04-2018.
 */

class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{

    private List<Notifications> mNotifList;

    private FirebaseFirestore firebaseFirestore;
    private Context context;

    public NotificationsAdapter(Context context, List<Notifications> mNotifList) {

        this.mNotifList = mNotifList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notification, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        firebaseFirestore = FirebaseFirestore.getInstance();

        String from_id = mNotifList.get(position).getFrom();

        holder.mNotifMessage.setText(mNotifList.get(position).getMessage());

        firebaseFirestore.collection("Users").document(from_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String name = documentSnapshot.getString("name");
                String image = documentSnapshot.getString("image");

                holder.mNotifName.setText(name);

                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.download);

                Glide.with(context).setDefaultRequestOptions(requestOptions).load(image).into(holder.mNotifImage);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mNotifList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ImageView mNotifImage;
        public TextView mNotifName;
        public TextView mNotifMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            mNotifImage = (ImageView) mView.findViewById(R.id.notif_image);
            mNotifMessage = (TextView) mView.findViewById(R.id.notif_message);
            mNotifName = (TextView) mView.findViewById(R.id.notif_name);


        }
    }

}
