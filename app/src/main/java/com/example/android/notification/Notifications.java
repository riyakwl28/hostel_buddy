package com.example.android.notification;

/**
 * Created by Riya Khandelwal on 15-04-2018.
 */

class Notifications {

    String from, message;

    public Notifications() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notifications(String from, String message) {
        this.from = from;
        this.message = message;
    }
}

