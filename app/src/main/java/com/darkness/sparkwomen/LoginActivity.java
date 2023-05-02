package com.darkness.sparkwomen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {



    TextInputEditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        findViewById(R.id.goToRegisterText).setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            LoginActivity.this.finish();
        });


        findViewById(R.id.loginBtn).setOnClickListener(view -> loginUser(Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString()));
    }

 //Login Activity
    private void loginUser(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    LoginActivity.this.finish();
                }else {
                    Toast.makeText(LoginActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

 //Resume Activity
    @Override
    protected void onResume() {
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            LoginActivity.this.finish();
        }
    }
}