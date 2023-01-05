package com.Netanel.glutenfreerestaurant.MyUtils;

import com.Netanel.glutenfreerestaurant.UserInformation.User;

public class CurrentUser {

    //singleton
    private static User currentUser = null;



    public static User getInstance(User user){
        if(currentUser == null){
            currentUser = user;
           // currentUser = new User(user.getName(), user.getPassword());
        }
        return currentUser;
    }
    public static User getInstance2(){
        return currentUser;
    }
//    public void restCurrentUser(){
//        currentUser = null;
//    }
}
