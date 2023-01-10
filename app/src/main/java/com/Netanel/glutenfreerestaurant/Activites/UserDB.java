package com.Netanel.glutenfreerestaurant.Activites;

import com.Netanel.glutenfreerestaurant.Model.Order;

import java.util.ArrayList;

public class UserDB {
    private ArrayList<Order> allOrders;
    private Order currentOrder;



    public UserDB() {
        this.allOrders = new ArrayList<Order>();
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(ArrayList<Order> allOrders) {
        this.allOrders = allOrders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
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
