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

import java.util.HashMap;

public class IndividualEventActivity extends AppCompatActivity {

    String eventID;
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

                Event event = new Event(_title, _date, _time, _location, _maxAttendees, _description, eventID, _creatorUsername, _creatorID);
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
    }
}