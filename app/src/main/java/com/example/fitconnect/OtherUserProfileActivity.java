package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OtherUserProfileActivity extends AppCompatActivity {

    String otherUserID;
    TextView textViewFullName, textViewLocation, textViewInterests, textViewDisplayName, textViewAboutMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);
        otherUserID = getIntent().getStringExtra("creatorID");
        System.out.println("IN OTHER USER CREATOR ID IS " + otherUserID);
    }
}