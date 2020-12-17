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
    private ArrayList<String> attendingUserIDs;
    private String eventID;
    private String creatorUsername;
    private String creatorID;

    public Event(String title, String date, String time, String location, String maxAttendees, String description, String eventID, String creatorUsername, String creatorID, ArrayList<String> attentingUserIDs){
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.description = description;
        this.eventID = eventID;
        this.creatorUsername = creatorUsername;
        this.creatorID = creatorID;
        this.attendingUserIDs = attentingUserIDs;
    }

    public ArrayList<String> getAttendingUserIDs() {
        return attendingUserIDs;
    }

    public void setAttendingUserIDs(ArrayList<String> attendingUserIDs) {
        this.attendingUserIDs = attendingUserIDs;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
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
        attendingUserIDs.add(user.getUserID());
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

    public ArrayList<String> getAttendingUsers() {
        return attendingUserIDs;
    }

    public void setAttendingUsers(ArrayList<String> attendingUsers) {
        this.attendingUserIDs = attendingUsers;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", maxAttendees='" + maxAttendees + '\'' +
                ", creatorUsername='" + creatorUsername + '\'' +
                '}';
    }
}
