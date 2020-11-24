package com.example.fitconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class DetailedSignupActivity extends AppCompatActivity {

    List<ActivityCategory>searchList;
    ListView listView_searchList;
    SearchView searchView_ds_search;
    ArrayAdapter<String > adapter;
    ArrayList<String> stringSearchList;
    ArrayList<String> userInterests;
    TextView textView_userInterests;
    Button button_finish;
    EditText editText_aboutMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_signup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        searchView_ds_search = findViewById(R.id.searchView_ds_search);
        listView_searchList = findViewById(R.id.listView_searchList);
        textView_userInterests = findViewById(R.id.textView_ds_interests);
        button_finish = findViewById(R.id.button_ds_finish);
        editText_aboutMe = findViewById(R.id.editText_ds_aboutMe);
        userInterests = new ArrayList<>();
        searchList = new ArrayList<ActivityCategory>(EnumSet.allOf(ActivityCategory.class));
        stringSearchList = new ArrayList<>();
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.getCurrentUser().setPreferredActivities(userInterests.toString());
                CurrentUser.getCurrentUser().setAboutMe(editText_aboutMe.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("users").child(CurrentUser.getCurrentUser().getUserID());
                ref.setValue(CurrentUser.getCurrentUser());
                Intent intent = new Intent(DetailedSignupActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        for(ActivityCategory i : searchList){
            stringSearchList.add(i.getLabel().toLowerCase());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stringSearchList);
        listView_searchList.setAdapter(adapter);
        searchView_ds_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.toLowerCase();
                if(stringSearchList.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(DetailedSignupActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        listView_searchList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String activity = (String) adapterView.getItemAtPosition(i);
                adapter.remove(activity);
                stringSearchList.remove(activity);
                adapter.notifyDataSetChanged();
                userInterests.add(activity);
                updateInterests();
            }
        });
    }

    private void updateInterests(){
        String interests = userInterests.toString();
        interests = interests.replace("[", "");
        interests = interests.replace("]", "");
        textView_userInterests.setText(interests);
    }
}