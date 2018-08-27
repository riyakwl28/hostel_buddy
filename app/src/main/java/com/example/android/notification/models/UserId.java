package com.example.android.notification.models;

import android.support.annotation.NonNull;

/**
 * Created by Riya Khandelwal on 13-04-2018.
 */

public class UserId {
    public String userId;
    public<T extends UserId> T withId(@NonNull final String id){
        this.userId=id;
        return (T) this;
    }
}
