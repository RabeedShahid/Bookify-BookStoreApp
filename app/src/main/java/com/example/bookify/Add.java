package com.example.bookify;

import static android.view.View.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookify.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.UUID;

public class Add extends Fragment
{
    EditText etbook_name,etprice,etdelivery,etquantity,etdescription,etauthor,etnumber;

    Button AddBtn;
    FirebaseAuth fAuth;
    long i=0;

    DatabaseReference refer;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        etquantity = view.findViewById(R.id.etquantity);
        etbook_name = view.findViewById(R.id.etbook_name);
        etdescription = view.findViewById(R.id.etdescrip);
        etprice = view.findViewById(R.id.etprice);
        etdelivery = view.findViewById(R.id.etdelevery);
        etauthor= view.findViewById(R.id.etauthor);
        etnumber=view.findViewById(R.id.etnumber);
                AddBtn = view.findViewById(R.id.AddBtn);


        AddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = etbook_name.getText().toString();
                String bookDescription = etdescription.getText().toString();
                String bookPrice = etprice.getText().toString();
                String deliveryPrice = etdelivery.getText().toString();
                String bookQuantity = etquantity.getText().toString();
                String bookauthor = etauthor.getText().toString();
                String bookcontact= etnumber.getText().toString();

                //ADD ID HERE
                refer = FirebaseDatabase.getInstance().getReference("bookDetails").child(String.valueOf(i));
                i++;

                //THEN ADD IN HASHMAP
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("name",bookName);
                hashMap.put("description",bookDescription);
                hashMap.put("price",bookPrice);
                hashMap.put("delivery",deliveryPrice);
                hashMap.put("quantity",bookQuantity);
                hashMap.put("author",bookauthor);
                hashMap.put("contact",bookcontact);
                FirebaseDatabase.getInstance().getReference().child("bookDetails").push()
                        .setValue(hashMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Book Added Sucessfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        return view;
    }
}