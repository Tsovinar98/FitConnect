package com.example.fitconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventActivity extends AppCompatActivity {

    EditText editTextTitle;
    EditText editTextDate;
    EditText editTextTime;
    EditText editTextLocation;
    EditText editTextMaxAttendees;
    EditText editTextDescription;
    Button buttonCreateEvent;

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
        buttonCreateEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createEvent();
            }
        });
    }

    private void createEvent(){
        final String title = editTextTitle.getText().toString();
        final String date = editTextDate.getText().toString();
        final String time = editTextTime.getText().toString();
        final String location = editTextLocation.getText().toString();
        final String numAttendees = editTextMaxAttendees.getText().toString();
        final String description = editTextDescription.getText().toString();

    }
}