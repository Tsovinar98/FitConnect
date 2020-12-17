package com.example.fitconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    TextView textView_fullName;
    TextView textView_displayName;
    ImageButton imageButton_settingsIcon;
    FirebaseUser currentUser;
    ListView listViewUpcoming;
    String creatorID;
    private static CustomAdapter adapter;
    ArrayList<Event> upcomingEvents = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        textView_fullName = findViewById(R.id.textView_pa_fullName);
        textView_displayName = findViewById(R.id.textView_pa_displayName);
        imageButton_settingsIcon = findViewById(R.id.imageButton_settingsIcon);
        listViewUpcoming = findViewById(R.id.listView_upcoming);
        imageButton_settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEvent();
            }
        });

        fillProfile();
        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch(position){
                            case 0:
                                tab.setText("PROFILE");
                                break;
                            case 1:
                                tab.setText("UPCOMING EVENTS");
                                break;
                            case 2:
                                tab.setText("MY EVENTS");
                                break;
                        }
                    }
                }).attach();
    }

    public void expandActivity(String eventID){
        Intent intent = new Intent(this, IndividualEventActivity.class);
        intent.putExtra("eventID", eventID);
        startActivity(intent);
    }

   public void findEventByID(String eventID){
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
               String _eventID = eventMap.get("eventID");
               Event event = new Event(_title, _date, _time, _location, _maxAttendees, _description, _eventID, _creatorUsername, _creatorID, (ArrayList<String>) attendingUserIDs);
               upcomingEvents.add(event);
               System.out.println("somehow added to upcoming events");
           }


           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
   }

    private ViewPagerAdapter createCardAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        return adapter;
    }

    private void fillProfile(){
        textView_fullName.setText(CurrentUser.getCurrentUser().getFirstName() + " " + CurrentUser.getCurrentUser().getLastName());
        textView_displayName.setText(CurrentUser.getCurrentUser().getDisplayName());
    }

    public void createEvent(){
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }
}
