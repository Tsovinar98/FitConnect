package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class IndividualEventActivity extends AppCompatActivity {

    String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_event);
        eventID = getIntent().getStringExtra("eventID");
    }
}