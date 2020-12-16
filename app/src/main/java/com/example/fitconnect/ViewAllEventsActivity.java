package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
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
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);
        listView = findViewById(R.id.list);
        getAllEvents();
    }


    public void getAllEvents(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventRef = ref.child("events");
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<Object, Object> eventsHashmap = (HashMap<Object,Object>) dataSnapshot.getValue();
                ArrayList<Object> allEventsArrayList = new ArrayList<Object>(eventsHashmap.values());
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

                adapter= new CustomAdapter(events,getApplicationContext());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Event dataModel= events.get(position);
                        String eventID = dataModel.getEventID();
                        expandActivity(eventID);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void expandActivity(String eventID){
        Intent intent = new Intent(this, IndividualEventActivity.class);
        intent.putExtra("eventID", eventID);
        startActivity(intent);
    }

}