package com.example.bookify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    EditText reset;
    Button sent, log;

    //Firebase
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //ViewBinding
        reset = findViewById(R.id.reset);
        sent = findViewById(R.id.sent);
        log = findViewById(R.id.log);
        firebaseAuth=FirebaseAuth.getInstance();

        log.setOnClickListener(view -> {
            startActivity(new Intent(forgot_password.this, login2.class));

        });

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     firebaseAuth.sendPasswordResetEmail(reset.getText().toString())
                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             if(task.isSuccessful()){
                                                 Toast.makeText(forgot_password.this, "Password reset link sent to your email", Toast.LENGTH_SHORT).show();
                                             }else{
                                                 Toast.makeText(forgot_password.this,"Error sending email", Toast.LENGTH_SHORT).show();

                                             }
                                         }
                                     });
            }
        });
    }
}
