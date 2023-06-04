package com.example.bookify;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;


public class fragment_profile extends Fragment {
    TextView first_name,email,address,phone_no;
    Button logout;
    FirebaseAuth mAuth;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_fragment_profile, container, false);
        first_name= view.findViewById(R.id.first_name);
        email= view.findViewById(R.id.email);
        address= view.findViewById(R.id.address);
        phone_no= view.findViewById(R.id.phone_no);


        logout= view.findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();;
                startActivity(new Intent( getActivity(), login2.class));
                getActivity().finish();
                Toast.makeText(getContext(), "logout successfully", Toast.LENGTH_SHORT).show();
            }
        });
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("profileInfo").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user user_ = snapshot.getValue(user.class);
                first_name.setText(user_.getName());
                email.setText(user_.getEmail());
                address.setText(user_.getAddress());
                phone_no.setText(user_.getContactNo());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
   return view;
    }
}