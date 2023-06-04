package com.example.bookify;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        splashScreen();
        {
            Thread thread = new Thread()
            {
                public void run()
                {
                    try {
                        sleep(1000);
                         if(fAuth.getCurrentUser() != null){
                             startActivity(new Intent(MainActivity.this, home_seller.class));

                         }else
                         {
                             startActivity(new Intent(MainActivity.this,login2.class));

                         }finish();
                    }
                    catch (Exception e)
                    {
                    }  }  };
            thread.start();
        } }
    private void splashScreen() {
    }
}