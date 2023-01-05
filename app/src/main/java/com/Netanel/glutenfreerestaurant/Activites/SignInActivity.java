package com.Netanel.glutenfreerestaurant.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.Netanel.glutenfreerestaurant.MyUtils.CurrentUser;
import com.Netanel.glutenfreerestaurant.MyUtils.FireBaseOperations;
import com.Netanel.glutenfreerestaurant.MyUtils.MySignal;
import com.Netanel.glutenfreerestaurant.R;
import com.Netanel.glutenfreerestaurant.UserInformation.User;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    private ShapeableImageView signIn_IMG_background;
    private TextInputEditText signIn_TXT_phoneNumber;
    private TextInputEditText signIn_TXT_Password;
    private Button signIn_BTN_signIn;
    private final String KEY_USER = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
        Glide.with(this).load(R.drawable.mainbackground).into(signIn_IMG_background);
        signIn_BTN_signIn.setOnClickListener(view ->clicked());


    }

    private void clicked() {
        FireBaseOperations.getInstance().getDatabaseReference(KEY_USER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Get User Information
                checkUser(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void checkUser(DataSnapshot snapshot) {
        //Check if user not exist in DB
        String phoneNumber = signIn_TXT_phoneNumber.getText().toString();
        if(snapshot.child(phoneNumber).exists()){
            //Get userInformation
            User user = snapshot.child(signIn_TXT_phoneNumber.getText().toString()).getValue(User.class);
            if(user.getPassword().equals(signIn_TXT_Password.getText().toString())){
                MySignal.getInstance().toast("Sign in successfully!");
                CurrentUser.getInstance(user);//save the current User Until SignOut
                Log.d("This is current user2",CurrentUser.getInstance2().toString());//checking with Tom
                changeScreen();
            }
            else{
                MySignal.getInstance().toast("Sign in failed!!!");
                MySignal.getInstance().vibrate();
            }

        }
        else{
            MySignal.getInstance().toast("User not exist in DB");
            MySignal.getInstance().vibrate();

        }




        }

    private void changeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    private void initViews() {
        signIn_IMG_background = findViewById(R.id.signIn_IMG_background);
        signIn_TXT_phoneNumber = findViewById(R.id.signIn_TXT_phoneNumber);
  //      signIn_TXT_name = findViewById(R.id.signIn_TXT_name);
        signIn_TXT_Password = findViewById(R.id.signIn_TXT_Password);
        signIn_BTN_signIn = findViewById(R.id.signIn_BTN_signIn);

    }
}