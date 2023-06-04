package com.example.bookify;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;


public class homec_fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<SellingBook> list;
    ABAdapter abAdapter;

     EditText editText;

    FirebaseUser firebaseUser;
    DatabaseReference refer;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homec_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        editText=view.findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
          filter(s.toString());
    }
});
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        list = new ArrayList<>();

        refer = FirebaseDatabase.getInstance().getReference("bookDetails");

        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    SellingBook book = snapshot1.getValue(SellingBook.class);
                    list.add(book);
                }

                abAdapter = new ABAdapter(getContext(), list);
                recyclerView.setAdapter(abAdapter);
                abAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
private void  filter(String text){
        ArrayList<SellingBook> filteredList =new ArrayList<>();

for (SellingBook item: list){
        if(item.getName().toLowerCase().contains(text.toLowerCase())){
            filteredList.add(item);

        }
        }
        abAdapter.filteredList(filteredList);}




}
