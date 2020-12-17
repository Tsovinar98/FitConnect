package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    FloatingActionButton fab;
    private static CustomAdapter adapter;
    TextView textViewLocationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);
        listView = findViewById(R.id.list);
        fab = findViewById(R.id.floatingActionButton);
        textViewLocationLabel = findViewById(R.id.textView_vae_location);
        textViewLocationLabel.setText(CurrentUser.getCurrentUser().getLocationLabel());
        getAllEvents();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Animation aniFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabfadein);
                Animation aniFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabfadeout);
                if(scrollState == SCROLL_STATE_TOUCH_SCROLL){
                    fab.animate().cancel();
                    fab.startAnimation(aniFadeOut);
                    fab.setVisibility(View.INVISIBLE);
                }else{
                    fab.animate().cancel();
                    fab.startAnimation(aniFadeIn);
                    fab.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateEventPage();
            }
        });
    }

    public void goToCreateEventPage(){
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
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
                    String _creatorID = eventMap.get("creatorID");
                    Object OattendingUserIDs =  eventMap.get("attendingUserIDs");
                    System.out.println(OattendingUserIDs.toString());
                    ArrayList<String> attendingUserIDs = (ArrayList) OattendingUserIDs;

                    Event event = new Event(_title, _date, _time, _location, _maxAttendees, _description, _eventID, _creatorUsername, _creatorID, (ArrayList<String>) attendingUserIDs);
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