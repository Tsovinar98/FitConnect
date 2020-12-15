package com.example.fitconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    TextView textView_fullName;
    TextView textView_displayName;
    ImageButton imageButton_settingsIcon;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        textView_fullName = findViewById(R.id.textView_pa_fullName);
        textView_displayName = findViewById(R.id.textView_pa_displayName);
        imageButton_settingsIcon = findViewById(R.id.imageButton_settingsIcon);

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
                                tab.setText("ATTENDED EVENTS");
                                break;
                        }
                    }
                }).attach();
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
