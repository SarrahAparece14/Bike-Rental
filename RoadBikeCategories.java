package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.rentabike.HomeAdapter.FeaturedAdapter;
import com.example.rentabike.HomeAdapter.FeaturedHelperClass;

import java.util.ArrayList;

public class RoadBikeCategories extends AppCompatActivity {

    RecyclerView RCategories,RCategories2;
    private GradientDrawable gradient1, gradiant2;
    RecyclerView.Adapter adapter;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_bike_categories);


        //Hooks
        RCategories = findViewById(R.id.categories1);
        RCategories2 = findViewById(R.id.categories2);
        backBtn = findViewById(R.id.back_pressed);
        RCategories();
        RCategories2();
    }

    private void RCategories() {
        RCategories.setHasFixedSize(true);
        RCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.aero_roadbike, "Aero Road Bike", "Price: P1,000.00/hr     Those who want to go efficiently and fast on flat roads."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.fitness_bike, "Fitness Bikes", "Price: P1,000.00    Riders who like commuting around the city."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.sportive, "Sportive Bike", "Price: P1,000.00/hr     If you like to enjoy speed without losing comfort."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.race_road, "Race Road Bikes", "Price: P1,500.00    Race road bikes are made for riders who want to take part in races and always achieve good results."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.triathlon, "Triathlon Bike", "Price: P3,000.00/hr     Competitors who are looking for time trial competitions or triathlon where every second count."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.flat_bar, "Flat Bar Bikes", "Price: P1,000.00   Ideal for riders who don’t take the fastest position on the drop bars."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.gravelbike, "Gravel Bike", "Price: P1,000.00/hr     Riders who want to go on long journeys with everything necessary with them."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.cyclo, "Cyclocross Bikes", "Price: P1,000.00    Riders who like competing with a geeky approach."));

        adapter = new FeaturedAdapter(featuredBike);

        adapter = new FeaturedAdapter(featuredBike);
        RCategories.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    private void RCategories2() {
        RCategories2.setHasFixedSize(true);
        RCategories2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.track, "Track Bike", "Price: P1,000.00/hr      track bike features a single, fixed gear, usually with no freehub"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.e_bike, "E-Bike Bikes", "Price: P1,000.00    E-bikes, which feature an electric motor to power the bike"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.dual_sport, "Dual Sport Bike", "Price: P1,000.00/hr     Dual-Sport bikes are, effectively, hybrid bikes for riders wanting to do more with the bike than with a typical hybrid."));
        featuredBike.add(new FeaturedHelperClass(R.drawable.bikepacking, "Bikepacking Bikes", "Price: P1,500.00    Set up and accessorized to enable the carrying of gear "));
        featuredBike.add(new FeaturedHelperClass(R.drawable.aluminum, "Aluminum Bike", "Price: P1,000.00/hr     Aluminum is the original lightweight material for bike frames, and wheels. "));

        adapter = new FeaturedAdapter(featuredBike);

        adapter = new FeaturedAdapter(featuredBike);
        RCategories2.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }

    public void back(View view) {
        RoadBikeCategories.super.onBackPressed();
    }
}