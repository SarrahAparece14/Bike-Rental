package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Bike_Available_List extends AppCompatActivity {
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike__available__list);

        //Hooks
        backBtn=findViewById(R.id.back_pressed);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bike_Available_List.super.onBackPressed();

            }
        });
    }

    public void expandWbike(View view) {
        Intent intent = new Intent(Bike_Available_List.this, WomenBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bike_Available_List.this);
        startActivity(intent, options.toBundle());

    }

    public void expandMbike(View view) {
        Intent intent = new Intent(Bike_Available_List.this, MountainBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bike_Available_List.this);
        startActivity(intent, options.toBundle());
    }

    public void expandEbike(View view) {
        Intent intent = new Intent(Bike_Available_List.this, ElectrickBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bike_Available_List.this);
        startActivity(intent, options.toBundle());
    }

    public void expandRbike(View view) {
        Intent intent = new Intent(Bike_Available_List.this, RoadBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bike_Available_List.this);
        startActivity(intent, options.toBundle());
    }
}