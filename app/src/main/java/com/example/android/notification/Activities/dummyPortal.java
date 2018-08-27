package com.example.android.notification.Activities;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HP on 14-04-2018.
 */

public class dummyPortal extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
