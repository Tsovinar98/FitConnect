package com.example.fitconnect;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TextView textView_signup;
    Button button_loginButton;
    EditText editText_email;
    EditText editText_password;
    TextView textView_forgotPassword;
    ProgressBar progressBar_loadingSpinner;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    DatabaseReference databaseUser;
    public static FirebaseAuth auth;
    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference();
        textView_signup = findViewById(R.id.textView_signup);
        button_loginButton = (Button) findViewById(R.id.button_login);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        textView_forgotPassword = findViewById(R.id.textView_forgotPassword);
        progressBar_loadingSpinner = findViewById(R.id.progressBar_loadingSpinner);
        progressBar_loadingSpinner.setVisibility(View.INVISIBLE);
        textView_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        textView_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        button_loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                progressBar_loadingSpinner.setVisibility(View.VISIBLE);
                login();
            }
        });
    }

    public void login(){
        final String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            final FirebaseUser user = auth.getCurrentUser();

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference userRef = ref.child("users").child(user.getUid());
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Object userObj = dataSnapshot.getValue();
                                    HashMap<String,String> map = (HashMap<String,String>) userObj;
                                    String _firstName = map.get("firstName");
                                    String _lastName = map.get("lastName");
                                    String _displayName = map.get("displayName");
                                    String _preferredActivities = map.get("preferredActivities");
                                    String _location = map.get("location");
                                    String _aboutMe = map.get("aboutMe");
                                    String _userID = map.get("userID");
                                    String _email = map.get("email");
                                    UserInformation _userInfo = new UserInformation(_userID, _displayName, _firstName, _lastName, _email, _aboutMe, _location, _preferredActivities);
                                    CurrentUser.setCurrentUser(_userInfo);
                                    Intent intent = new Intent(LoginActivity.this, ViewAllEventsActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        progressBar_loadingSpinner.setVisibility(View.INVISIBLE);
                    }
                });
    }
}