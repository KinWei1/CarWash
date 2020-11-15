package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.w3c.dom.Text;

public class list_car_wash extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mCarWashList;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car_wash);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mCarWashList = findViewById(R.id.car_wash_list);

        //Query

        Query query= firebaseFirestore.collection("Car Wash").orderBy("Price");


        //Recycler options
        FirestoreRecyclerOptions<car_wash_model> options = new FirestoreRecyclerOptions.Builder<car_wash_model>()
                .setQuery(query, car_wash_model.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<car_wash_model, car_wash_viewHolder>(options) {
            @NonNull
            @Override
            public car_wash_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.car_wash_list_single, parent, false);
                return new car_wash_viewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull car_wash_viewHolder holder, int position, @NonNull car_wash_model model) {
                holder.list_shop_name.setText(model.getCarWashName());
                holder.list_shop_phone.setText(model.getPhoneNumber());
                holder.list_shop_price.setText(model.getPrice());
            }
        };

        mCarWashList.setHasFixedSize(true);
        mCarWashList.setLayoutManager(new LinearLayoutManager(this));
        mCarWashList.setAdapter(adapter);

    }




    private class car_wash_viewHolder extends RecyclerView.ViewHolder {

        private TextView list_shop_name;
        private TextView list_shop_phone;
        private TextView list_shop_price;

        public car_wash_viewHolder(@NonNull View itemView) {
            super(itemView);

            list_shop_name = itemView.findViewById(R.id.list_shop_name);
            list_shop_phone = itemView.findViewById(R.id.list_shop_phone_number);
            list_shop_price = itemView.findViewById(R.id.list_shop_price);


        }



    }
    @Override
    protected  void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected  void onStart() {
        super.onStart();
        adapter.startListening();
    }


   public void Appointment (View view){
        startActivity(new Intent(this, UserAppointment.class));

    }



}