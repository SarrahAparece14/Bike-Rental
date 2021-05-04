package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Header extends AppCompatActivity {

    TextView fullNameLabel, usernameLabel, emailLabel;
    String _USERNAME, _NAME, _EMAIL;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);


        reference= FirebaseDatabase.getInstance().getReference("Users");


        //Hooks to all xml elements in activity_sign_up.xml
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        emailLabel=findViewById(R.id.email_field);

        //Show All Data
        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent=getIntent();

        _USERNAME=intent.getStringExtra("username");
        _NAME=intent.getStringExtra("name");
        _EMAIL=intent.getStringExtra("email");


        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        emailLabel.setText(_EMAIL);

    }
}