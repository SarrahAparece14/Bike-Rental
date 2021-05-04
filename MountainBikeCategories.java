package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.rentabike.HomeAdapter.FeaturedAdapter;
import com.example.rentabike.HomeAdapter.FeaturedHelperClass;

import java.util.ArrayList;

public class MountainBikeCategories extends AppCompatActivity {
    RecyclerView RCategories,RCategories2;
    private GradientDrawable gradient1, gradiant2;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_bike_categories);


        RCategories=findViewById(R.id.categories1);
        RCategories2=findViewById(R.id.categories2);

        RCategories();
        RCategories2();

    }

    private void RCategories() {
        RCategories.setHasFixedSize(true);
        RCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.battle_excellence670, "Battle Excellence", "Price: P1,000.00/hr     Good for rough terrain"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.phoenix, "Phoenix", "Price: P1,000.00    Ergonomic and trendy design"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.inches21, "21-Speed Phoenix", "Price: P1,000.00/hr     Best for children "));
        featuredBike.add(new FeaturedHelperClass(R.drawable.inches26, "26inches Bike", "Price: P1,500.00    Budget pick"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.jackpot, "Jackpot 20 Bike", "Price: P3,000.00/hr    The cheapest "));

        adapter = new FeaturedAdapter(featuredBike);

        adapter = new FeaturedAdapter(featuredBike);
        RCategories.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    private void RCategories2() {
        RCategories2.setHasFixedSize(true);
        RCategories2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.savadeck600, "SAVA Deck 300", "Price: P1,000.00/hr     Best overall"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.battle_250, "Battle 590-D", "Price: P1,000.00    Good value for money"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.savesetsail, "SAVA Set Sail", "Price: P1,000.00/hr     All-around performance"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.vmax, "VMAX", "Price: P1,500.00    24 speed foldable bike"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.foldingbike, "Folding Bike", "Price: P3,000.00/hr     Budget foldable bike"));
        adapter = new FeaturedAdapter(featuredBike);

        adapter = new FeaturedAdapter(featuredBike);
        RCategories2.setAdapter(adapter);

        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }

    public void back(View view) {
        MountainBikeCategories.super.onBackPressed();
    }
}