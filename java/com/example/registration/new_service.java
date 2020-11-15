package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class new_service extends AppCompatActivity {


    EditText mShopName, mPhoneNumber, mShopAddress, mDescription, mPrice;
    Button mButton;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);

        mFirestore = FirebaseFirestore.getInstance();

        mShopName = findViewById(R.id.shopname);
        mPhoneNumber = findViewById(R.id.phonenumber);
        mShopAddress = findViewById(R.id.shopaddress);
        mDescription = findViewById(R.id.description);
        mPrice = findViewById(R.id.price);
        mButton = findViewById(R.id.addservice);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shopname = mShopName.getText().toString();
                String phonenumber = mPhoneNumber.getText().toString();
                String shopaddress = mShopAddress.getText().toString();
                String description = mDescription.getText().toString();
                String price = mPrice.getText().toString();

                Map<String, String> userMap = new HashMap<>();

                userMap.put("CarWashName", shopname);
                userMap.put("PhoneNumber", phonenumber);
                userMap.put("ShopAddress", shopaddress);
                userMap.put("Description", description);
                userMap.put("Price", price);


                if (shopname.isEmpty()) {
                    mShopName.setError("Please enter your shop name");
                    mShopName.requestFocus();
                } else if (phonenumber.isEmpty()) {
                    mPhoneNumber.setError("Please enter your phone number");
                    mPhoneNumber.requestFocus();
                } else if (shopaddress.isEmpty()) {
                    mShopAddress.setError("Please enter your shop address");
                    mShopAddress.requestFocus();
                } else if (description.isEmpty()) {
                    mDescription.setError("Please enter your description");
                    mDescription.requestFocus();
                } else if (price.isEmpty()) {
                    mPrice.setError("Please enter your description");
                    mPrice.requestFocus();
                } else {

                    mFirestore.collection("Car Wash ").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            startActivity(new Intent(new_service.this, company_home.class));
                            Toast.makeText(new_service.this, "Service has been added into Carwash category", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                            Toast.makeText(new_service.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    public EditText getshopname() {
        return mShopName;
    }

}

