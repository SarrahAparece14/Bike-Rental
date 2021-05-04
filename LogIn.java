package com.example.rentabike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);

        //Hooks
        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.app_logo);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.Login_btn);

        //Call SignUp screen
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "app_logo");
                pairs[1] = new Pair<View, String>(logoText, "logo_name");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(username, "username_tran");
                pairs[4] = new Pair<View, String>(password, "password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LogIn.this, pairs);
                startActivity(intent, options.toBundle());
            }


        });


    }

        private Boolean validateUsername () {
            String val = username.getEditText().getText().toString();

            if (val.isEmpty()) {
                username.setError("Field cannot be empty");
                return false;
            } else {
                username.setError(null);
                username.setErrorEnabled(false);
                return true;
            }

        }

        private Boolean validatePassword () {
            String val = password.getEditText().getText().toString();

            if (val.isEmpty()) {
                password.setError("Field cannot be empty");
                return false;
            } else {
                password.setError(null);
                password.setErrorEnabled(false);
                return true;
            }

        }


        public void loginNow (View view){
            //Validation of Info upon log in
            if (!validateUsername() | !validatePassword()) {
                return;
            } else {
                isUser();

            }
        }

        private void isUser () {

            final String userEnteredUsername = username.getEditText().getText().toString().trim();
            String userEnteredPassword = password.getEditText().getText().toString().trim();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

            checkUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                        if (passwordFromDB.equals(userEnteredPassword)) {
                            username.setError(null);
                            username.setErrorEnabled(false);

                            String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                            String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                            String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                            String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);


                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("phoneNo", phoneNoFromDB);
                            intent.putExtra("password", passwordFromDB);

                            startActivity(intent);
                        } else {
                            password.setError("Wrong Password");
                            password.requestFocus();
                        }
                    } else {
                        username.setError("No such user exist");
                        username.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {


                }
            });
        }



}






