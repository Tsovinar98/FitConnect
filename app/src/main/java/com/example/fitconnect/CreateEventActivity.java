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

    EditText editTextTextPersonName;
    EditText editTextDate;
    EditText editTextTime;
    EditText editTextTextPostalAddress;
    EditText editTextNumAttendees;
    EditText editTextTextPersonName2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextTextPostalAddress = findViewById(R.id.editTextTextPostalAddress);
        editTextNumAttendees = findViewById(R.id.editTextNumAttendees);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createEvent();
            }
        });
    }

    private void createEvent(){
        final String title = editTextTextPersonName.getText().toString();
        final String date = editTextDate.getText().toString();
        final String time = editTextTime.getText().toString();
        final String location = editTextTextPostalAddress.getText().toString();
        final String numAttendees = editTextTextPostalAddress.getText().toString();
        final String description = editTextTextPersonName2.getText().toString();

    }
}