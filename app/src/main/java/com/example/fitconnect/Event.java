package com.example.fitconnect;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String title;
    private String description;
    private UserInformation creatingUser;
    private Date date;
    private boolean hasDeterminedLocation;
    private Location location;
    private ArrayList<UserInformation> attendingUsers;
    private int maxAttendees;
    private int numAttending;

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

    public boolean isHasDeterminedLocation() {
        return hasDeterminedLocation;
    }

    public void setHasDeterminedLocation(boolean hasDeterminedLocation) {
        this.hasDeterminedLocation = hasDeterminedLocation;
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

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public int getNumAttending() {
        return numAttending;
    }

    public void setNumAttending(int numAttending) {
        this.numAttending = numAttending;
    }
}
