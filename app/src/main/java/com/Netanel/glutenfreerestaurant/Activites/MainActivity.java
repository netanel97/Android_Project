package com.Netanel.glutenfreerestaurant.Activites;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.Netanel.glutenfreerestaurant.MyUtils.Constants;
import com.Netanel.glutenfreerestaurant.MyUtils.FireBaseOperations;
import com.Netanel.glutenfreerestaurant.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public static FirebaseUser currentUser = null;
    public static UserDB userDB;
    private DatabaseReference reference = FireBaseOperations.getInstance().getDatabaseReference(Constants.USER_DB);
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
    }


    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> onSignInResult(result)
    );
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
    }

    private void createNewUserDB() {
        userDB = new UserDB();
        userDB.setName(currentUser.getDisplayName());
        reference.child(currentUser.getUid()).setValue(userDB);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                reference.setValue(currentUser.getUid());
                reference = reference.child(currentUser.getUid());
                reference.setValue(userDB);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void login(FirebaseUser currentUser) {
        if(currentUser == null) {//not found in DB
            // Choose authentication providers

            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            // Create and launch sign-in intent
            Intent signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers).setLogo(R.drawable.glutenfree).setTheme(R.style.Theme_GlutenFreeRestaurant_AppBarOverlay).setIsSmartLockEnabled(false)
                    .build();
            signInLauncher.launch(signInIntent);
            check = true;
        }
        else{
            // TODO: 1/11/2023 need to check how to enter to the value here without boolean..
            if(check) {
                createNewUserDB();
                switchScreen();
            }
            else {
                loadUserFromDB();
            }
        }
        }

    /**
     * Getting the current user by uid
     */
    private void loadUserFromDB() {
        reference = reference.child(currentUser.getUid());
        Log.d("reference", "loadUserFromDB: " + reference.getKey());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userDB = snapshot.getValue(UserDB.class);
                switchScreen();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void switchScreen() {
        if (userDB == null)
            userDB = new UserDB();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        login(currentUser);
    }
}