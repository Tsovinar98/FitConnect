package com.example.fitconnect;
import java.util.ArrayList;

public class Event {
    private String title;
    private String description;
    private UserInformation creatingUser;
    private String date;
    private String time;
    private String location;
    private String maxAttendees;
    private ArrayList<UserInformation> attendingUsers;
    private String eventID;
    private String creatorUsername;

    public Event(String title, String date, String time, String location, String maxAttendees, String description, String eventID, String creatorUsername){
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.description = description;
        this.eventID = eventID;
        this.creatorUsername = creatorUsername;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(String maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
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

    public ArrayList<UserInformation> getAttendingUsers() {
        return attendingUsers;
    }

    public void setAttendingUsers(ArrayList<UserInformation> attendingUsers) {
        this.attendingUsers = attendingUsers;
    }

}
