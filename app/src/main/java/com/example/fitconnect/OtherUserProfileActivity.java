package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OtherUserProfileActivity extends AppCompatActivity {

    String otherUserID;
    TextView textViewFullName, textViewLocation, textViewInterests, textViewDisplayName, textViewAboutMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);
        otherUserID = getIntent().getStringExtra("creatorID");
        System.out.println("IN OTHER USER CREATOR ID IS " + otherUserID);
        textViewFullName = findViewById(R.id.textView_ou_fullName);
        textViewAboutMe = findViewById(R.id.textView_ou_aboutMe);
        textViewDisplayName = findViewById(R.id.textView_ou_displayName);
        textViewLocation = findViewById(R.id.textView_ou_location);
        textViewInterests = findViewById(R.id.textView_ou_interests);
        findUser();
    }

    public void findUser(){
        final FirebaseUser user = LoginActivity.auth.getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = ref.child("users").child(otherUserID);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object userObj = dataSnapshot.getValue();
                HashMap<String,String> map = (HashMap<String,String>) userObj;
                String _firstName = map.get("firstName");
                String _lastName = map.get("lastName");
                String _displayName = map.get("displayName");
                String _preferredActivities = map.get("preferredActivities");
                String _location = map.get("location");
                String _aboutMe = map.get("aboutMe");
                String _userID = map.get("userID");


                textViewFullName.setText(_firstName + " " + _lastName);
                textViewAboutMe.setText(_aboutMe);
                textViewLocation.setText(_location);
                textViewInterests.setText(_preferredActivities);
                textViewDisplayName.setText(_displayName);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}