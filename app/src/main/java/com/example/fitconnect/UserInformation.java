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
    private String preferredActivities;
    private ArrayList<UserInformation> blockedUsers;

    //constructor for logging in
    public UserInformation(String userID, String displayName, String firstName, String lastName, String email, String aboutMe, String location, String preferredActivities) {
        this.userID = userID;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.aboutMe = aboutMe;
        this.location = location;
        this.preferredActivities = preferredActivities;
    }

    //Constructor used for signing up
    public UserInformation(String userID, String displayName, String firstName, String lastName, String email, String address) {
        this.userID = userID;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.location = address;
        this.preferredActivities = "";
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

    public String getPreferredActivities() {
        return preferredActivities;
    }

    public void setPreferredActivities(String preferredActivities) {
        this.preferredActivities = preferredActivities;
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
