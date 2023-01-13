package com.Netanel.glutenfreerestaurant.MyUtils;

import android.app.Application;

import com.Netanel.glutenfreerestaurant.Model.UserDB;
import com.google.firebase.auth.FirebaseUser;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FireBaseOperations.getInstance();
        MySignal.init(this);




    }


}
