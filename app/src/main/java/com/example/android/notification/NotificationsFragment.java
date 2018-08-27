package com.example.android.notification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {


    private RecyclerView mNotificationList;
    private NotificationsAdapter notificationsAdapter;

    private List<Notifications> mNotifList;
    private FirebaseFirestore mFirestore;


    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_notifications, container, false);


        mNotifList = new ArrayList<>();

        mNotificationList = (RecyclerView) v.findViewById(R.id.notification_list);
        notificationsAdapter = new NotificationsAdapter(getContext(), mNotifList);



        mNotificationList.setHasFixedSize(true);
        mNotificationList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mNotificationList.setAdapter(notificationsAdapter);

        mFirestore = FirebaseFirestore.getInstance();

        String current_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();


        mFirestore.collection("Users").document(current_user_id).collection("Notifications").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc: documentSnapshots.getDocumentChanges()) {

                    Notifications notifications = doc.getDocument().toObject(Notifications.class);
                    mNotifList.add(notifications);

                    notificationsAdapter.notifyDataSetChanged();

                }

            }
        });


        return v;
    }

}
