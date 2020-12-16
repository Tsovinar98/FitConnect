package com.example.fitconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;

public class CreateEventActivity extends AppCompatActivity {

    EditText editTextTitle;
    EditText editTextDate;
    EditText editTextTime;
    EditText editTextLocation;
    EditText editTextMaxAttendees;
    EditText editTextDescription;
    Button buttonCreateEvent;
    ImageButton imageButton_cancel;
    ImageButton imageButton_profile;
    String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        editTextTitle = findViewById(R.id.editText_ce_eventTitle);
        editTextDate = findViewById(R.id.editText_ce_date);
        editTextTime = findViewById(R.id.editText_ce_time);
        editTextLocation = findViewById(R.id.editText_ce_location);
        editTextMaxAttendees = findViewById(R.id.editText_ce_maxAttendees);
        editTextDescription = findViewById(R.id.editText_ce_description);
        buttonCreateEvent = findViewById(R.id.button_ce_createEvent);
        imageButton_cancel = findViewById(R.id.imageButton_ce_cancel);
        imageButton_profile = findViewById(R.id.imageButton_ce_profile);
        buttonCreateEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createEvent();
            }
        });

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

    private void createEvent(){
        final String title = editTextTitle.getText().toString();
        final String date = editTextDate.getText().toString();
        final String time = editTextTime.getText().toString();
        final String location = editTextLocation.getText().toString();
        final String numAttendees = editTextMaxAttendees.getText().toString();
        final String description = editTextDescription.getText().toString();

        System.out.println(title);
        System.out.println(date);
        System.out.println(time);
        System.out.println(location);
        System.out.println(numAttendees);
        System.out.println(description);
        //get numEvents from database to use as an ID
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventRef = ref.child("numEvents");
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object userObj = dataSnapshot.getValue();
                System.out.println("numEvents is " + userObj);
                eventID = userObj.toString();
                System.out.println("in here eventID is " + eventID);
                writeEventToUser(title, date, time, location, numAttendees, description);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void writeEventToUser(String title, String date, String time, String location, String maxAttendees, String description){
        FirebaseUser user = LoginActivity.auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userid = user.getUid();
        System.out.println("value of event id is"+ eventID);
        DatabaseReference eref = database.getReference("users").child(userid).child("events").child(eventID);
        Event event = new Event(title, date, time, location, maxAttendees, description, eventID, CurrentUser.getCurrentUser().getDisplayName(), CurrentUser.getCurrentUser().getUserID());
        System.out.println("the event is " + event.toString());
        eref.setValue(event);

        writeEventToDatabase(event);
    }

    public void writeEventToDatabase(Event event){
        FirebaseUser user = LoginActivity.auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userid = user.getUid();
        System.out.println("value of event id is"+ eventID);
        DatabaseReference eref = database.getReference("events").child(eventID);
        eref.setValue(event);

        updateEventNum();
    }

    public void updateEventNum(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eref = database.getReference("numEvents");
        eref.setValue(Integer.parseInt(eventID)+1);

        postConfirmation();
    }

    public void postConfirmation(){
        Intent intent = new Intent(this, IndividualEventActivity.class);
        intent.putExtra("eventID", eventID);
        startActivity(intent);
    }
}