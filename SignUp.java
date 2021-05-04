package com.example.rentabike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLogInBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);


    }

    //validate the data you input
    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();


        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String regularExpression = "^[a-zA-Z0-9._-]{8,}$";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length()>=15){
            regUsername.setError("Username too long");
            return false;
        }else if(!val.matches(regularExpression)){
            regUsername.setError("White Spaces are not allowed");
            return false;

        }
        else {
            regUsername.setError(null);
            return true;
        }

    }
    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;

        } else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;

        } else {
            regEmail.setError(null);
            return true;
        }

    }
    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();


        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }

    }
    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal="^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        //Password must contain at least one digit [0-9].
        //Password must contain at least one uppercase Latin character [A-Z].
        //Password must contain at least one special character like ! @ # & ( ).
        //Password must contain a length of at least 8 characters and a maximum of 20 characters.

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if(!val.matches(passwordVal)){
            regPassword.setError("Password is too weak");
            return false;
        }

        else {
            regPassword.setError(null);
            return true;
        }

    }


    //save data in FireBase on button click
    public void registerUser(android.view.View view) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        if (!validateName() | !validateEmail() | !validatePassword() | !validatePhoneNo() | !validateUsername())
        {
            return;
        }

        //Get all the value in String
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();


         UserHelperClass helperclass = new UserHelperClass(name, username, email, phoneNo, password);
        //create a child that will display first in the database
        reference.child(username).setValue(helperclass);

       Toast.makeText(this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LogIn.class);
        startActivity(intent);
        finish();

    }


    public void callLogin(View view) {
        Intent intent = new Intent(SignUp.this, LogIn.class);
        startActivity(intent);
    }

    }




