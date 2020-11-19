package com.example.fitconnect;

import android.location.Location;

import java.util.ArrayList;

public class UserInformation {
    private String userID;
    private String displayName;
    private String firstName;
    private String lastName;
    private String email;
    private String aboutMe;
    private String photoUrl;
    private String location;
    private ArrayList<ActivityCategory> preferredActivities;
    private ArrayList<UserInformation> blockedUsers;

    public UserInformation(String userID, String displayName, String firstName, String lastName, String email, String location) {
        this.userID = userID;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = location;
        this.preferredActivities = new ArrayList<>();
        this.aboutMe = "";
    }

    public UserInformation(String email) {
        this.email = email;
        this.preferredActivities = new ArrayList<>();
        this.aboutMe = "";
    }

    public String getUserID() {
        return userID;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public ArrayList<ActivityCategory> getPreferredActivities() {
        return preferredActivities;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
