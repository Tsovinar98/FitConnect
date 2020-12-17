package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IndividualEventActivity extends AppCompatActivity {

    String eventID;
    String creatorID;
    ImageButton imageButton_cancel;
    ImageButton imageButton_profile;
    Button button_attending;
    TextView textView_title;
    TextView textView_date;
    TextView textView_time;
    TextView textView_maxAttendees;
    TextView textView_creatorUsername;
    TextView textView_location;
    TextView textView_description;
    ImageButton imageButton_share;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_event);
        System.out.println("made it here");
        eventID = getIntent().getStringExtra("eventID");
        imageButton_cancel = findViewById(R.id.imageButton_ie_cancel);
        imageButton_profile = findViewById(R.id.imageButton_ie_profile);
        button_attending = findViewById(R.id.button_ie_attend);
        textView_title = findViewById(R.id.textView_ie_title);
        textView_date = findViewById(R.id.textView_ie_date);
        textView_time = findViewById(R.id.textView_ie_time);
        textView_maxAttendees = findViewById(R.id.textView_ie_attendees);
        textView_creatorUsername = findViewById(R.id.textView_ie_username);
        textView_location = findViewById(R.id.textView_ie_location);
        textView_description = findViewById(R.id.textView_ie_description);
        imageButton_share = findViewById(R.id.imageButton_ie_share);
        readIndividualEvent();

        imageButton_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });

        imageButton_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAllEvents();
            }
        });

        button_attending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attendClicked();
            }
        });

        imageButton_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareEvent();
            }
        });

        textView_creatorUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreatorProfile();
            }
        });
    }

    public void attendClicked(){
        button_attending.setText("✓ Attending Activity");
        CurrentUser.getCurrentUser().addEvent(event);
        CurrentUser.getCurrentUser().addEvent(event.getEventID());
        System.out.println("Event attended, arraylist is now " + CurrentUser.getCurrentUser().getUpcomingTest().size());
    }

    public void shareEvent(){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        String shareSubject = "I found this cool event on FitConnect!\n";
        String shareBody = textView_title.getText().toString();
        shareBody = shareBody.concat("\n" + textView_date.getText().toString() + " | " + textView_time.getText().toString());
        shareBody = shareBody.concat("\n" + textView_location.getText().toString());
        shareBody = shareBody.concat("\n" + textView_description.getText().toString());
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSubject);
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        /*Fire!*/
        startActivity(Intent.createChooser(intent, "Share"));
    }

    public void goToCreatorProfile(){
        Intent intent = new Intent(this, OtherUserProfileActivity.class);
        intent.putExtra("creatorID", creatorID);
        startActivity(intent);
    }

    public void goToProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToAllEvents(){
        Intent intent = new Intent(this, ViewAllEventsActivity.class);
        startActivity(intent);
    }

    public void readIndividualEvent(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventRef = ref.child("events").child(eventID);
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object eventObj = dataSnapshot.getValue();
                HashMap<String,String> eventMap = (HashMap<String,String>) eventObj;
                String _creatorUsername = eventMap.get("creatorUsername");
                String _date = eventMap.get("date");
                String _description = eventMap.get("description");
                String _location = eventMap.get("location");
                String _maxAttendees = eventMap.get("maxAttendees");
                String _time = eventMap.get("time");
                String _title = eventMap.get("title");
                String _creatorID = eventMap.get("creatorID");
                creatorID = eventMap.get("creatorID");
                Object OattendingUserIDs =  eventMap.get("attendingUserIDs");
                System.out.println(OattendingUserIDs.toString());
                ArrayList<String> attendingUserIDs = (ArrayList) OattendingUserIDs;

                event = new Event(_title, _date, _time, _location, _maxAttendees, _description, eventID, _creatorUsername, _creatorID, (ArrayList<String>) attendingUserIDs);
                updateUI(event);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void updateUI(Event event){
        textView_title.setText(event.getTitle());
        textView_date.setText(event.getDate());
        textView_time.setText(event.getTime());
        textView_maxAttendees.setText(event.getMaxAttendees());
        textView_creatorUsername.setText(event.getCreatorUsername());
        textView_location.setText(event.getLocation());
        textView_description.setText(event.getDescription());
        if(CurrentUser.getCurrentUser().getUpcomingEvents().contains(event.getEventID())){
            button_attending.setText("✓ Attending Activity");
        }
    }
}