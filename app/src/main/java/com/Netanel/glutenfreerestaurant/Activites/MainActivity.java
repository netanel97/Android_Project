package com.Netanel.glutenfreerestaurant.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.Netanel.glutenfreerestaurant.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
    private ShapeableImageView main_IMG_background;
    private Button main_BTN_signUp;
    private Button main_BTN_signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Glide.with(this).load(R.drawable.mainbackground).into(main_IMG_background);

        main_BTN_signIn.setOnClickListener(view ->switchScreen(true));
        main_BTN_signUp.setOnClickListener(view -> switchScreen(false));
    }
   /*
   if screen = True --> SignIn else SignUp
    */
    private void switchScreen(boolean screen) {
        if(screen){
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();

        }

    }

    private void initViews() {
        main_IMG_background = findViewById(R.id.main_IMG_background);
        main_BTN_signUp = findViewById(R.id.main_BTN_signUp);
        main_BTN_signIn = findViewById(R.id.main_BTN_signIn);

    }
}