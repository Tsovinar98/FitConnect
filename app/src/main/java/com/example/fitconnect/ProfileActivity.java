package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView test;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        currentUser = (FirebaseUser) getIntent().getExtras().get("currentUser");
        String name = currentUser.getDisplayName();
        test.setText(name);
        System.out.println(name);
    }
}
