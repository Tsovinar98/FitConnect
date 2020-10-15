package com.example.fitconnect;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView textView_signup;
    Button button_loginButton;
    EditText editText_email;
    EditText editText_password;
    TextView textView_forgotPassword;
    ProgressBar progressBar_loadingSpinner;
    public static FirebaseAuth auth;
    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
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
            }
        });

        textView_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
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
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                            intent.putExtra("currentUser", user);
                            startActivity(intent);
                            //updateUI(user);
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