package com.example.android.notification.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.notification.Activities.UsersListActivity;
import com.example.android.notification.R;
import com.example.android.notification.UsersRecyclerAdapter;
import com.example.android.notification.models.Users;
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
public class UsersFragment extends Fragment {
    private RecyclerView mUsersListView;
    private List<Users> usersList;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private FirebaseFirestore mFirestore;


    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_users, container, false);
        mFirestore=FirebaseFirestore.getInstance();
        mUsersListView=(RecyclerView)view.findViewById(R.id.users_list_view);
        usersList=new ArrayList<>();
        usersRecyclerAdapter=new UsersRecyclerAdapter(container.getContext(),usersList);
        mUsersListView.setHasFixedSize(true);
        mUsersListView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mUsersListView.setAdapter(usersRecyclerAdapter);
   return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        usersList.clear();
        mFirestore.collection("Users").addSnapshotListener(getActivity(),new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
              for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                  if(doc.getType()==DocumentChange.Type.ADDED){

                      String user_id=doc.getDocument().getId();

                      Users users=doc.getDocument().toObject(Users.class).withId(user_id);
                      usersList.add(users);
                      usersRecyclerAdapter.notifyDataSetChanged();
                  }

              }
            }
        });
        }
    }

