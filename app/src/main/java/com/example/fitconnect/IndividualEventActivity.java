package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        readIndividualEvent();
    }

    public void readIndividualEvent(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventRef = ref.child("events").child(eventID);
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object userObj = dataSnapshot.getValue();
                System.out.println("numEvents is " + userObj);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}