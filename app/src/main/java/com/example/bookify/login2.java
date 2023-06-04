package com.example.bookify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login2 extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button   forgot,create,login;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        login = findViewById(R.id.login);
        create = findViewById(R.id.create);
        forgot = findViewById(R.id.forgot);
        fauth = FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            logUser();
        });

        forgot.setOnClickListener(view -> {
            startActivity(new Intent(login2.this, forgot_password.class));

        });

        create.setOnClickListener(view -> {
            startActivity(new Intent(login2.this, CreateAccount.class));

        });


    }

    private void logUser()
    {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if ( TextUtils.isEmpty(email)){
            emailInput.setError(" Please enter your Email Address");
            emailInput.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            emailInput.setError(" Please enter your Password");
            emailInput.requestFocus();
        }else{
            fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login2.this, "Logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( login2.this, home_seller.class));
                        finish();
                    }else{
                        Toast.makeText(login2.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}