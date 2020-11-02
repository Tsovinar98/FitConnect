package com.example.fitconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText resetEmail;
    Button resetButton;
    ProgressBar progressBar_loadingSpinner;
    private static String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        resetEmail = findViewById(R.id.editText_resetEmail);
        resetButton = findViewById(R.id.button_resetPassword);
        progressBar_loadingSpinner = findViewById(R.id.progressBar_fp_loadingSpinner);
        progressBar_loadingSpinner.setVisibility(View.INVISIBLE);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar_loadingSpinner.setVisibility(View.VISIBLE);
                String email = resetEmail.getText().toString();
                System.out.println(email);
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                                progressBar_loadingSpinner.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });
    }
}