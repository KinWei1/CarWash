package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class SelectionActivity<UserRegistration> extends AppCompatActivity {

    ImageView imageView, imageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        imageView = findViewById(R.id.imageView);
        imageView6 = findViewById(R.id.imageView6);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, ShopRegistration.class);
                startActivity(intent);
            }

        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, com.example.registration.UserRegistration.class);
                startActivity(intent);
            }
        });
    }
}
