package com.Netanel.glutenfreerestaurant.Model;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UserDB {
    private ArrayList<Order> allOrders;
    private Order currentOrder;
    private String name;
    private static UserDB userDB = null;

    private UserDB(){
        allOrders = new ArrayList<>();
        currentOrder = new Order();
    }


    private UserDB(FirebaseUser currentUser){
        allOrders = new ArrayList<>();
        currentOrder = new Order();
        this.name = currentUser.getDisplayName();

    }

    public static void init(FirebaseUser currentUser){
        if (userDB == null) {
            userDB = new UserDB(currentUser);
        }
    }
    public static UserDB getInstance(){return userDB;}

    public void setUser(UserDB user){
        this.name = user.name;
        this.currentOrder = user.currentOrder;
        this.allOrders = user.allOrders;
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }





    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order){

        allOrders.add(0,order);
    }


    public void setAllOrders(ArrayList<Order> allOrders) {
        this.allOrders = allOrders;
    }
}
