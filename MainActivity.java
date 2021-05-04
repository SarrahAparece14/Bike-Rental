package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN= 3000;
    //Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView appname, textView, slogan;
    int val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        //Hooks
        image=findViewById(R.id.app_logo);
        appname=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView2);

        image.setAnimation(bottomAnim);
        appname.setAnimation(topAnim);
        slogan.setAnimation(topAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this, LogIn.class);

                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"app_logo");
                pairs[1]=new Pair<View,String>(appname,"logo_text");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());


            }




        }, SPLASH_SCREEN);
    }
}