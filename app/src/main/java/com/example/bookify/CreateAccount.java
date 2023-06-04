package com.example.bookify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity {

    FirebaseAuth auth;
    EditText etemail,etpassword,etfirstname,etphone_no,etaddress;
    Button btn_account;
    DatabaseReference refer;
    long i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_customer);

        etfirstname = findViewById(R.id.first_name);
        etphone_no =findViewById(R.id.phone_no);
        etemail = findViewById(R.id.email);
        etpassword = findViewById(R.id.password);
        etaddress = findViewById(R.id.address);
        btn_account = findViewById(R.id.btn_account);

        auth = FirebaseAuth.getInstance();


        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = etfirstname.getText().toString();
              String phone_no = etphone_no.getText().toString();
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();
               String address = etaddress.getText().toString();
                if ( TextUtils.isEmpty(email)){
                    etemail.setError(" Please enter your Email Address");
                    etemail.requestFocus();
                }else if(etpassword.length()<8){
                    etpassword.setError("Password is less than 8 ");
                    etpassword.requestFocus();
                }else{
                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(CreateAccount.this, "Registered", Toast.LENGTH_SHORT).show();
                                    FirebaseUser firebaseUser = auth.getCurrentUser();
                                    refer = FirebaseDatabase.getInstance().getReference("profileInfo").child(firebaseUser.getUid());



                                    //THEN ADD IN HASHMAP
                                    HashMap<String,String> hashMap = new HashMap<>();
                                    hashMap.put("name",first_name);
                                    hashMap.put("email",email);
                                    hashMap.put("address",address);
                                    hashMap.put("contactNo",phone_no);

                                    refer.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(CreateAccount.this, "Profile created", Toast.LENGTH_SHORT).show();
                                                    Intent Intents= new Intent(CreateAccount.this, home_seller.class);
                                                    startActivity(Intents);
                                                    setContentView(R.layout.activity_home_seller);
                                                    finish();
                                                }

                                            }

                                    });
                                }
                            }
                        });}
            }


        });






    }
}
