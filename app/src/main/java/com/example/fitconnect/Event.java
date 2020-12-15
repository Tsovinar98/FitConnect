package com.example.fitconnect;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String title;
    private String description;
    private UserInformation creatingUser;
    private Date date;
    private String sDate;
    private String sTime;
    //private boolean hasDeterminedLocation;
    private Location location;
    private String sLocation;
    private String sAttendees;
    private ArrayList<UserInformation> attendingUsers;
    //private int maxAttendees;
    //private int numAttending;
    private String eventID;

    public Event(String title, String sdate, String stime, String slocation, String sAttendees, String description, String eventID){
        this.title = title;
        this.sDate = sdate;
        this.sTime = stime;
        this.sLocation = slocation;
        this.sAttendees = sAttendees;
        this.description = description;
        this.eventID = eventID;
    }

    public void addUser(UserInformation user){
        attendingUsers.add(user);
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserInformation getCreatingUser() {
        return creatingUser;
    }

    public void setCreatingUser(UserInformation creatingUser) {
        this.creatingUser = creatingUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<UserInformation> getAttendingUsers() {
        return attendingUsers;
    }

    public void setAttendingUsers(ArrayList<UserInformation> attendingUsers) {
        this.attendingUsers = attendingUsers;
    }

}
