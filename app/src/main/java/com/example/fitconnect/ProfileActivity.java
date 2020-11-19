package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {


    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        currentUser = (FirebaseUser) getIntent().getExtras().get("currentUser");
        String name = CurrentUser.getCurrentUser().getDisplayName();
        System.out.println(name);
    }
}
