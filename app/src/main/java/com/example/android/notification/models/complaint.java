package com.example.android.notification.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 14-04-2018.
 */

public class complaint implements Parcelable {
    private String title;
    private String type;
    private String description;
    private String username;
    private String profilePhotoUrl;
    private String complaintPhotoUrl;

    public complaint(){}

    public complaint(Parcel in){
        title = in.readString();
        type = in.readString();
        description = in.readString();
        username = in.readString();
        profilePhotoUrl = in.readString();
        complaintPhotoUrl = in.readString();

    }

    public complaint(String title, String type, String description,String username, String profilePhotoUrl,String complaintPhotoUrl){
        this.title = title;
        this.type = type;
        this.description = description;
        this.username = username;
        this.profilePhotoUrl = profilePhotoUrl;
        this.complaintPhotoUrl = complaintPhotoUrl;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getComplaintPhotoUrl() {
        return complaintPhotoUrl;
    }

    public void setComplaintPhotoUrl(String complaintPhotoUrl) {
        this.complaintPhotoUrl = complaintPhotoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);

        dest.writeString(type);
        dest.writeString(description);
        dest.writeString(username);
        dest.writeString(profilePhotoUrl);
        dest.writeString(complaintPhotoUrl);
    }

    public static final Parcelable.Creator<complaint> CREATOR = new Parcelable.Creator<complaint>(){
        public complaint createFromParcel(Parcel in) {
            return new complaint(in);
        }

        public complaint[] newArray(int size) {
            return new complaint[size];
        }
    };


}
