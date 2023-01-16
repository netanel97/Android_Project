package com.Netanel.glutenfreerestaurant.MyUtils;

import android.app.Application;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FireBaseOperations.getInstance();
        MySignal.init(this);




    }


}
