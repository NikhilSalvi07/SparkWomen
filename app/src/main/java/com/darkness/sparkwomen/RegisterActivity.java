package com.darkness.sparkwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    TextInputEditText name, email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        findViewById(R.id.goToLoginText).setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            RegisterActivity.this.finish();
        });


        findViewById(R.id.registerBtn).setOnClickListener(v ->
                registerUser(Objects.requireNonNull(name.getText()).toString(), Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString())
        );

        }

    //Register Activity
    private void registerUser(String name, String email, String password){
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "All Fields Are Required!", Toast.LENGTH_SHORT).show();
        }else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    RegisterActivity.this.finish();
                }else {
                    Toast.makeText(RegisterActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}