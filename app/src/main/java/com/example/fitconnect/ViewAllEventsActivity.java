package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ViewAllEventsActivity extends AppCompatActivity {

    ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);
        getAllEvents();
    }


    public void getAllEvents(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventRef = ref.child("events");
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Object> allEventsArrayList = (ArrayList<Object>) dataSnapshot.getValue();
                allEventsArrayList.removeAll(Collections.singleton(null));
                System.out.println("Events array list after null removed");
                System.out.println(allEventsArrayList.toString());

                for(int i =0; i < allEventsArrayList.size(); i++){
                    HashMap<String,String> eventMap = (HashMap<String,String>) allEventsArrayList.get(i);
                    String _creatorUsername = eventMap.get("creatorUsername");
                    String _date = eventMap.get("date");
                    String _description = eventMap.get("description");
                    String _location = eventMap.get("location");
                    String _maxAttendees = eventMap.get("maxAttendees");
                    String _time = eventMap.get("time");
                    String _title = eventMap.get("title");
                    String _eventID = eventMap.get("eventID");

                    Event event = new Event(_title, _date, _time, _location, _maxAttendees, _description, _eventID, _creatorUsername);
                    events.add(event);
                }

                updateUI();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void updateUI(){
        System.out.println("TESTMEDADDY");
        for(int i =0; i < events.size();i++){
            System.out.println(events.get(i).toString());
        }
    }
}