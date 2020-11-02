package com.example.fitconnect;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {

    Button button_signup;
    EditText editText_email;
    EditText editText_password;
    EditText editText_firstName;
    EditText editText_lastName;
    EditText editText_username;
    Button button_selectLocation;
    ProgressBar progressBar_loadingSpinner;
    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //initialize all elements
        button_signup = findViewById(R.id.button_su_signup);
        editText_email = findViewById(R.id.editText_su_email);
        editText_password = findViewById(R.id.editText_su_password);
        editText_firstName = findViewById(R.id.editText_su_firstName);
        editText_lastName = findViewById(R.id.editText_su_lastName);
        editText_username = findViewById(R.id.editText_su_username);
        button_selectLocation = findViewById(R.id.button_su_selectLocation);
        progressBar_loadingSpinner = findViewById(R.id.progressBar_su_loadingSpinner);

        //set the spinner to invisible
        progressBar_loadingSpinner.setVisibility(View.INVISIBLE);

        button_signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                progressBar_loadingSpinner.setVisibility(View.VISIBLE);
                signUp();
            }
        });
    }

    private void signUp(){
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        final String name = editText_firstName.getText().toString();
        LoginActivity.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = LoginActivity.auth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user.updateProfile(profileUpdates);
                            Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                            intent.putExtra("currentUser", user);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        progressBar_loadingSpinner.setVisibility(View.INVISIBLE);
                        // ...
                    }
                });
    }
}