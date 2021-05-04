package com.example.rentabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.rentabike.HomeAdapter.FeaturedAdapter;
import com.example.rentabike.HomeAdapter.FeaturedHelperClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    static final float END_SCALE=0.7f;

    RecyclerView featuredRecycler, mostRentRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradiant2, gradiant3, gradiant4;
    ImageView menuIcon;
    LinearLayout contentView;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


        //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);


        reference= FirebaseDatabase.getInstance().getReference("Users");
        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostRentRecycler = findViewById(R.id.rent_most);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView=findViewById(R.id.content);

       //email = findViewById(R.id.email_profile);
        //fullNameLabel = findViewById(R.id.fullname_field);
        //usernameLabel = findViewById(R.id.username_field);


        //Show All Data
       // showAllUserData();


        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation DRawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        navigationDrawer();

        //Recycler Views Function Calls
        featuredRecycler();
        mostRentRecycler();
        categoriesRecycler();

    }



    //Navigation Drawer FUnction
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.card4));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset=slideOffset *(1-END_SCALE);
                final float offsetScale= 1- diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset=drawerView.getWidth()*slideOffset;
                final float xOffsetDiff=contentView.getWidth()*diffScaledOffset/2;
                final float xTranslation=xOffset- xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }


        });
    }



    /*private void showAllUserData() {
        Intent intent=getIntent();

        _USERNAME=intent.getStringExtra("username");
        _NAME=intent.getStringExtra("name");
        _EMAIL=intent.getStringExtra("email");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullname.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
    }
*/



    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(),Dashboard.class));

                break;
            case R.id.nav_search:
                startActivity(new Intent(getApplicationContext(),SearchBike.class));
                break;

            case R.id.nav_categories:
                startActivity(new Intent(getApplicationContext(),Bike_Available_List.class));
                break;

            case R.id.nav_profile:

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                startActivity(new Intent(getApplicationContext(),Profile.class));

                break;
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(),LogIn.class));
                break;

            /* Add the other main_menu for connecting next page*/
        }
            drawerLayout.closeDrawer(GravityCompat.START);
             return true;
             }


    //Recycler View FUnction
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.roadbike, "Road Bike", "The bike that you love and easy to bring"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.mountain_bike, "Mountain Bike", "The bike that you love for mountain trails or Off trails"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.electric_bike, "Electric Bike", "E-bikes use rechargeable batteries that can travel you to your destination quicker and in better shape"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.women, "Women Bike", "It offers you the best combination of fit, comfort and style."));

        adapter = new FeaturedAdapter(featuredBike);


        adapter = new FeaturedAdapter(featuredBike);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }
    private void mostRentRecycler() {
                mostRentRecycler.setHasFixedSize(true);
                mostRentRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
               ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.roadbike, "Road Bike", "The bike that you love and easy to bring"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.mountain_bike, "Mountain Bike", "The bike that you love for mountain trails or Off trails"));

        adapter = new FeaturedAdapter(featuredBike);

      adapter = new FeaturedAdapter(featuredBike);
        mostRentRecycler.setAdapter(adapter);

        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }
    private void categoriesRecycler() {
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredBike = new ArrayList<>();

        featuredBike.add(new FeaturedHelperClass(R.drawable.roadbike, "Road Bike", "The bike that you love and easy to bring"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.electric_bike, "Electric Bike", "E-bikes use rechargeable batteries that can travel you to your destination quicker and in better shape"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.mountain_bike, "Mountain Bike", "The bike that you love for mountain trails or Off trails"));
        featuredBike.add(new FeaturedHelperClass(R.drawable.women, "Women Bike", "It offers you the best combination of fit, comfort and style."));

        adapter = new FeaturedAdapter(featuredBike);


        adapter = new FeaturedAdapter(featuredBike);
        categoriesRecycler.setAdapter(adapter);

        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }


    public void rbike(View view) {
        Intent intent = new Intent(Dashboard.this, RoadBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Dashboard.this);
        startActivity(intent, options.toBundle());
    }

    public void mbike(View view) {
        Intent intent = new Intent(Dashboard.this, MountainBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Dashboard.this);
        startActivity(intent, options.toBundle());

    }

    public void elbike(View view) {
        Intent intent = new Intent(Dashboard.this, ElectrickBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Dashboard.this);
        startActivity(intent, options.toBundle());
    }

    public void wobike(View view) {
        Intent intent = new Intent(Dashboard.this, WomenBikeCategories.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Dashboard.this);
        startActivity(intent, options.toBundle());
    }
}
