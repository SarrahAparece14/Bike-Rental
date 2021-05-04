package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    TextInputLayout fullname, username, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;
    String _USERNAME, _NAME, _EMAIL,__PHONENO, _PASSWORD;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        reference= FirebaseDatabase.getInstance().getReference("Users");

        //Hooks to all xml elements in activity_sign_up.xml
        fullname = findViewById(R.id.full_name_profile);
        username = findViewById(R.id.reg_username);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        //Show All Data
        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent=getIntent();

        _USERNAME=intent.getStringExtra("username");
        _NAME=intent.getStringExtra("name");
        _EMAIL=intent.getStringExtra("email");
        __PHONENO=intent.getStringExtra("phoneNo");
        _PASSWORD=intent.getStringExtra("password");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullname.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(__PHONENO);
        password.getEditText().setText(_PASSWORD);

    }


    public void gotoHome(View view) {
        Intent intent = new Intent(Profile.this, Dashboard.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Profile.this);
        startActivity(intent, options.toBundle());

    }

    public void update(View view) {
        if (isNameChanged() || isPasswordChanged() || isEmailChanged() || isPhoneNumberChanged()) {
            Toast.makeText(this, " Data has been updated", Toast.LENGTH_SHORT).show();

        }
        else Toast.makeText(this, " Data is the same, cannot be updated", Toast.LENGTH_SHORT).show();

    }

    private boolean isPhoneNumberChanged() {
        if(!__PHONENO.equals(phoneNo.getEditText().getText().toString())){
            reference.child(_USERNAME).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            return true;
        }
        else{
            return false;

        }


    }

    private boolean isEmailChanged() {
        if(!_EMAIL.equals(email.getEditText().getText().toString())){
            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
            return true;
        }
        else{
            return false;

        }


    }

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            return true;
        }
        else{
            return false;

        }

    }

    private boolean isNameChanged() {
        if(!_NAME.equals(fullname.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullname.getEditText().getText().toString());
            return true;
        }
        else{
            return false;

        }

    }


}
