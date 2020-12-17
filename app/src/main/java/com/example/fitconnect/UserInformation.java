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
    private String streetAddress = "";
    private String cityState = "";
    private String preferredActivities;
    private ArrayList<UserInformation> blockedUsers;
    private ArrayList<Event> attendedEvents;
    private ArrayList<String> upcomingEvents;
    private ArrayList<Event> myEvents;

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
        formatAddress();
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
        this.blockedUsers = new ArrayList<>();
        this.upcomingEvents = new ArrayList<>();
        this.attendedEvents = new ArrayList<>();
        this.myEvents = new ArrayList<>();
        formatAddress();
    }

    public String getLocationLabel(){
        int lastComma = cityState.lastIndexOf(",");
        String locationLabel = cityState.substring(0,lastComma-5);
        return locationLabel;
    }

    public void addEvent(Event e){
        this.myEvents.add(e);
    }

    private void formatAddress(){
        int numCommas = 0;
        for(int i =0; i < location.length(); i++){
            if(location.charAt(i) == ','){
                numCommas++;
            }
            if(numCommas == 0){
                streetAddress+=location.charAt(i);
            }else if(numCommas >= 1){
                cityState+=location.charAt(i);
            }
        }
        cityState = cityState.replaceFirst(" ",  "");
        cityState = cityState.replaceFirst(",", "");
        System.out.println("Street Address: " + streetAddress);
        System.out.println("CityState: " + cityState);
    }

    public void addEvent(String eventID){
        if(upcomingEvents==null){
            upcomingEvents = new ArrayList<>();
        }
        upcomingEvents.add(eventID);
        System.out.println("EVENT ADDED " + upcomingEvents.toString());
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
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
        this.preferredActivities = this.preferredActivities.replace("[", "");
        this.preferredActivities = this.preferredActivities.replace("]", "");
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
