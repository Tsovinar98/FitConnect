package com.example.fitconnect;

public class CurrentUser {

    public static UserInformation currentUser;
    public static UserInformation getCurrentUser(){
        return currentUser;
    }
    public static void setCurrentUser(UserInformation user){
        currentUser = user;
    }
}
