package com.Netanel.glutenfreerestaurant.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

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

public class SignUpActivity extends AppCompatActivity {
    private ShapeableImageView signUp_IMG_background;
    private TextInputEditText signUp_TXT_phoneNumber;
    private TextInputEditText signUp_TXT_name;
    private TextInputEditText signUp_TXT_Password;
    private Button signUp_BTN_signUp;
    private final String KEY_USER = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        Glide.with(this).load(R.drawable.mainbackground).into(signUp_IMG_background);
        signUp_BTN_signUp.setOnClickListener(view ->clicked());


    }

    private void clicked() {
        FireBaseOperations.getInstance().getDatabaseReference(KEY_USER).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checkRegister(snapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkRegister(DataSnapshot snapshot) {
        String phoneNumber = signUp_TXT_phoneNumber.getText().toString();
        if(snapshot.child(phoneNumber).exists()){
            MySignal.getInstance().toast("Phone Number already exists!");
        }
        else{
            String name = signUp_TXT_name.getText().toString();
            String password = signUp_TXT_Password.getText().toString();
            User user = new User(name,password);
            FireBaseOperations.getInstance().getDatabaseReference().child(phoneNumber).setValue(user);//adding user to DB
            MySignal.getInstance().toast("Sign up successfully");
        }

    }

    private void initViews() {
        signUp_IMG_background = findViewById(R.id.signUp_IMG_background);
        signUp_TXT_phoneNumber = findViewById(R.id.signUp_TXT_phoneNumber);
        signUp_TXT_name = findViewById(R.id.signUp_TXT_name);
        signUp_TXT_Password = findViewById(R.id.signUp_TXT_Password);
        signUp_BTN_signUp = findViewById(R.id.signUp_BTN_signUp);


    }
}