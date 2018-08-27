package com.example.android.notification.models;

/**
 * Created by sakshi on 16/4/18.
 */

public class To_Items {

    String to_item_name;
    String to_name;

    public To_Items()
    {  }

    public To_Items(String to_item_name, String to_name) {
        this.to_item_name = to_item_name;
        this.to_name = to_name;
    }

    public String getTo_item_name() {
        return to_item_name;
    }

    public void setTo_item_name(String to_item_name) {
        this.to_item_name = to_item_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }
}
