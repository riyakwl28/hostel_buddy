package com.example.android.notification.models;

/**
 * Created by sakshi on 16/4/18.
 */

public class From_Items {

    String from_item_name;
    String from_name;

    public From_Items()
    { }

    public From_Items(String from_item_name, String from_name) {
        this.from_item_name = from_item_name;
        this.from_name = from_name;
    }

    public String getFrom_item_name() {
        return from_item_name;
    }

    public void setFrom_item_name(String from_item_name) {
        this.from_item_name = from_item_name;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }
}
