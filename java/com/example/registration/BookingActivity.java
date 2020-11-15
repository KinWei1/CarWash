package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BookingActivity extends AppCompatActivity implements UserAppointment.OnAppointmentSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);
    }

    @Override
    public void onAppointmentSelected() {

    }
}
