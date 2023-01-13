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

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }





    public String totalPrice(){
        int totalPrice = 0;
        for (int i = 0; i < currentOrder.getAllFoods().size(); i++) {
            totalPrice += Integer.parseInt(currentOrder.getAllFoods().get(i).getPrice());

        }
        return Integer.toString(totalPrice);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order){
        allOrders.add(order);
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "allOrders=" + allOrders +
                ", currentOrder=" + currentOrder +
                '}';
    }
}
